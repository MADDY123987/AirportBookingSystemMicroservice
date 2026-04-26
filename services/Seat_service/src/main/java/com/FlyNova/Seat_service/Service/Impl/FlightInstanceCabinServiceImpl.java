package com.FlyNova.Seat_service.Service.Impl;

import com.FlyNova.Seat_service.Mapper.FlightInstanceCabinMapper;
import com.FlyNova.Seat_service.Model.CabinClass;
import com.FlyNova.Seat_service.Model.FlightInstanceCabin;
import com.FlyNova.Seat_service.Model.SeatMap;
import com.FlyNova.Seat_service.Repository.CabinClassRepository;
import com.FlyNova.Seat_service.Repository.FlightInstanceCabinRepository;
import com.FlyNova.Seat_service.Repository.SeatMapRepository;
import com.FlyNova.Seat_service.Service.FlightInstanceCabinService;
import com.FlyNova.payload.request.FlightInstanceCabinRequest;
import com.FlyNova.payload.response.FlightInstanceCabinResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FlightInstanceCabinServiceImpl implements FlightInstanceCabinService {

    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;
    private final FlightInstanceCabinRepository flightInstanceCabinRepository;

    @Override
    public FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(request.getCabinClassId()).orElseThrow(
                ()->new Exception("Cabin Class Not Found")
        );
        SeatMap seatMap=seatMapRepository.findByCabinClassId(cabinClass.getId());
        if(seatMap==null){
            throw new Exception("Seat Map Not Found");
        }
        if(seatMap.getSeats()==null||seatMap.getSeats().isEmpty())
        {
            throw new Exception("No Seat Found in Seat Map");
        }
        int totalSeat=seatMap.getSeats().size();
        FlightInstanceCabin cabin=FlightInstanceCabin.builder()
                .flightInstanceId(request.getFlightInstanceId())
                .cabinClass(cabinClass)
                .totalSeats(totalSeat)
                .BookedSeats(0)
                .build();
        FlightInstanceCabin savedCabin= flightInstanceCabinRepository.save(cabin);
        return FlightInstanceCabinMapper.toResponse(savedCabin);

        //todo:generate Seats Instanc after  do creating the cbain pls
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
        return flightInstanceCabinRepository.findBYFlightInstanceId(id,pageable)
                .map(FlightInstanceCabinMapper::toResponse);
    }

    @Override
    public FlightInstanceCabinResponse getByFlightInstanceIdAndCabinClassId(Long flightInstanceId, Long cabinClassId) {
        FlightInstanceCabin cabin= flightInstanceCabinRepository.findFlightInstanceIdAndCabinClassId(flightInstanceId,cabinClassId);
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
}
