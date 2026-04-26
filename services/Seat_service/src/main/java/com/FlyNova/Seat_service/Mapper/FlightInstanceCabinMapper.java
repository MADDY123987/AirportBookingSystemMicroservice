package com.FlyNova.Seat_service.Mapper;

import com.FlyNova.Seat_service.Model.FlightInstanceCabin;
import com.FlyNova.payload.response.FlightInstanceCabinResponse;

import java.util.stream.Collectors;

public class FlightInstanceCabinMapper {
    public static FlightInstanceCabinResponse toResponse(FlightInstanceCabin fic) {
        if (fic == null) return null;
        return FlightInstanceCabinResponse.builder()
                .id(fic.getId())
                .flightInstanceId(fic.getFlightInstanceId())
                .cabinClassType(fic.getCabinClass() != null ? fic.getCabinClass().getName() : null)
                .cabinClass(fic.getCabinClass() != null ?
                        CabinClassMapper.toResponse(fic.getCabinClass(), fic.getCabinClass().getSeatMap()) : null)
                //todo:watch set Seat Intance
                //.seats(fic.geteats() != null ?
                       // fic.getSeats().stream().map(SeatInstanceMapper::toResponse)
                               // .collect(Collectors.toList()) : null)
                .SeatMap(fic.getCabinClass() != null && fic.getCabinClass().getSeatMap() != null ?
                        SeatMapMapper.toSimpleResponse(fic.getCabinClass().getSeatMap()) : null)
                .totalSeats(fic.getTotalSeats())
                .bookedSeats(fic.getBookedSeats())
                .availableSeats(fic.getAvailableSeats())
                .build();
    }
}
