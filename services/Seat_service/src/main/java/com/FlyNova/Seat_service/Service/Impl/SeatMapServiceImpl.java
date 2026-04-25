package com.FlyNova.Seat_service.Service.Impl;

import com.FlyNova.Seat_service.Mapper.SeatMapMapper;
import com.FlyNova.Seat_service.Model.CabinClass;
import com.FlyNova.Seat_service.Model.SeatMap;
import com.FlyNova.Seat_service.Repository.CabinClassRepository;
import com.FlyNova.Seat_service.Repository.SeatMapRepository;
import com.FlyNova.Seat_service.Service.SeatMapService;
import com.FlyNova.Seat_service.Service.SeatService;
import com.FlyNova.payload.request.SeatMapRequest;
import com.FlyNova.payload.response.SeatMapResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SeatMapServiceImpl implements SeatMapService {
    private final CabinClassRepository cabinClassRepository;
    private final SeatMapRepository seatMapRepository;
    private final SeatService seatService;

    @Override
    public SeatMapResponse CreateSeatMap(Long airlineId, SeatMapRequest request) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(request.getCabinClassId())
                .orElseThrow(
                        ()->new Exception("Cabin Class Not Found With Given Id")
                );
        if(seatMapRepository.existsByAirlineIdAndCabinClassIdAndName
                (airlineId,request.getCabinClassId(), request.getName()))
        {
            throw new Exception("Cabin Class Already Exists With Given Name");
        }
        SeatMap seatMap= SeatMapMapper.toEntity(request,cabinClass);
        seatMap.setAirlineId(airlineId);
        SeatMap savedSeatMap=seatMapRepository.save(seatMap);

        //todo:generate Seats for SeatMap Automaticll for the SeatMap
        seatService.generateSeats(savedSeatMap.getId());

        return SeatMapMapper.toResponse(savedSeatMap);
    }

    @Override
    public SeatMapResponse getSeatMapById(Long id) throws Exception {
        SeatMap seatMap=seatMapRepository.findById(id)
                .orElseThrow(
                        ()->new Exception("Seat Map Not Found with id")
        );
        return SeatMapMapper.toResponse(seatMap);
    }

    @Override
    public SeatMapResponse getSeatMapByCabinClass(Long cabinId) {
        SeatMap seatMap=seatMapRepository.findByCabinClassId(cabinId);
        return SeatMapMapper.toResponse(seatMap);
    }

    @Override
    public SeatMapResponse updateSeatMap(Long id, SeatMapRequest request) throws Exception {
        SeatMap seatMap=seatMapRepository.findById(id).orElseThrow(
                ()->new Exception("Seat Map Not Found With Id")
        );
        SeatMapMapper.updateEntity(request,seatMap);
        SeatMap updated=seatMapRepository.save(seatMap);
        return SeatMapMapper.toResponse(updated);
    }

    @Override
    public void deleteSeatMap(Long id) throws Exception {
        if (!seatMapRepository.existsById(id)) {
            throw new Exception("Seat map not found with id: " + id);
        }
        seatMapRepository.deleteById(id);
    }
}
