package com.FlyNova.Seat_service.Service.Impl;

import com.FlyNova.Seat_service.Mapper.FlightInstanceCabinMapper;
import com.FlyNova.Seat_service.Model.CabinClass;
import com.FlyNova.Seat_service.Model.FlightInstanceCabin;
import com.FlyNova.Seat_service.Model.SeatInstance;
import com.FlyNova.Seat_service.Model.SeatMap;
import com.FlyNova.Seat_service.Repository.*;
import com.FlyNova.Seat_service.Service.FlightInstanceCabinService;
import com.FlyNova.enums.SeatAvailabilityStatus;
import com.FlyNova.enums.SeatType;
import com.FlyNova.payload.request.FlightInstanceCabinRequest;
import com.FlyNova.payload.response.FlightInstanceCabinResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;
    private final SeatRepository seatRepository;
    private final SeatInstanceRepository seatInstanceRepository;

    @Override
    public FlightInstanceCabinResponse createFlightInstanceCabin(
            FlightInstanceCabinRequest request) throws Exception {
        CabinClass cabinClass = cabinClassRepository.findById(request.getCabinClassId())
                .orElseThrow(() -> new Exception("Cabin Class Not Found"));

        SeatMap seatMap = seatMapRepository.findByCabinClassId(cabinClass.getId());
        if (seatMap == null) {
            throw new Exception("Seat Map Not Found");
        }
        if (seatMap.getSeats() == null || seatMap.getSeats().isEmpty()) {
            throw new Exception("No Seat Found in Seat Map");
        }
        int totalSeat = seatMap.getSeats().size();
        FlightInstanceCabin cabin = FlightInstanceCabin.builder()
                .flightInstanceId(request.getFlightInstanceId())
                .cabinClass(cabinClass)
                .totalSeats(totalSeat)
                .BookedSeats(0)
                .build();
        FlightInstanceCabin savedCabin = flightInstanceCabinRepository.save(cabin);
        List<SeatInstance> seatInstances = seatMap.getSeats().stream()
                .map(seat -> {
                    Double premiumSurcharge = getPremiumSurcharge(
                            seat.getSeatType(),
                            1000.0,
                            500.0
                    );
                    return SeatInstance.builder()
                            .flightId(request.getFlightId())
                            .status(SeatAvailabilityStatus.AVAILABLE)
                            .flightInstanceId(request.getFlightInstanceId())
                            .flightInstanceCabin(savedCabin)
                            .seat(seat)
                            .isAvailable(true)
                            .isBooked(false)
                            .premiumSupercharge(premiumSurcharge)
                            .build();
                })
                .toList();
        seatInstanceRepository.saveAll(seatInstances);
        savedCabin.setSeats(seatInstances);
        return FlightInstanceCabinMapper.toResponse(savedCabin);
    }

    @Override
    public FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) throws Exception {
        FlightInstanceCabin fic= flightInstanceCabinRepository.findById(id)
                .orElseThrow(
                        ()->new Exception("Flight Instance Cabin Not Found with id:"+id)
                );
        return FlightInstanceCabinMapper.toResponse(fic);
    }

    @Override
    public Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long id, Pageable pageable) {
        return flightInstanceCabinRepository.findByFlightInstanceId(id,pageable)
                .map(FlightInstanceCabinMapper::toResponse);
    }

    @Override
    public FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId) {
        FlightInstanceCabin cabin= flightInstanceCabinRepository.findByFlightInstanceIdAndCabinClassId(flightInstanceId,cabinClassId);
        return FlightInstanceCabinMapper.toResponse(cabin);
    }

    @Override
    public FlightInstanceCabinResponse updateFlightInstanceCabin(Long id, FlightInstanceCabinRequest request) throws Exception {
        FlightInstanceCabin fic= flightInstanceCabinRepository.findById(id)
                .orElseThrow(()->new Exception("Flight Instance Cabin Not Found With id"+id));
        if(request.getCabinClassId()!=null){
            CabinClass cabinClass=cabinClassRepository.findById(request.getCabinClassId())
                    .orElseThrow(()->new EntityNotFoundException("Cabin Class Not Found"));
            fic.setCabinClass(cabinClass);
        }
        FlightInstanceCabin updated= flightInstanceCabinRepository.save(fic);
        return FlightInstanceCabinMapper.toResponse(updated);
    }

    @Override
    public void deleteFlightInstanceCabin(Long id) {
        FlightInstanceCabin fic = flightInstanceCabinRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Flight instance cabin not found with id: " + id));
        flightInstanceCabinRepository.delete(fic);
    }
    private Double getPremiumSurcharge(SeatType seatType,
                                       Double windowSurcharge,
                                       Double aisleSurcharge) {
        if (seatType == null) return 0.0;

        return switch (seatType) {
            case Window -> windowSurcharge != null ? windowSurcharge : 0.0;
            case AISLE -> aisleSurcharge != null ? aisleSurcharge : 0.0;
            default -> 0.0;
        };
    }
}
