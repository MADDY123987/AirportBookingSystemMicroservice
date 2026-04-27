package com.FlyNova.Seat_service.Mapper;

import com.FlyNova.Seat_service.Model.CabinClass;
import com.FlyNova.Seat_service.Model.Seat;
import com.FlyNova.Seat_service.Model.SeatMap;
import com.FlyNova.payload.request.SeatMapRequest;
import com.FlyNova.payload.response.SeatMapResponse;

import java.util.List;


public class SeatMapMapper {
    public static SeatMap toEntity(SeatMapRequest request,
                                   CabinClass cabinClass) {
        return SeatMap.builder()
                .name(request.getName())
                .totalRows(request.getTotalRows())
                .leftSeatsPerRow(request.getLeftSeatsPerRow())
                .rightSeatsPerRow(request.getRightSeatsPerRow())
                .cabinClass(cabinClass)
                .build();
    }

    public static void updateEntity(SeatMapRequest request, SeatMap seatMap) {
        seatMap.setName(request.getName());
        seatMap.setTotalRows(request.getTotalRows());
        seatMap.setLeftSeatsPerRow(request.getLeftSeatsPerRow());
        seatMap.setRightSeatsPerRow(request.getRightSeatsPerRow());
    }

    public static SeatMapResponse toResponse(SeatMap seatMap) {
        if (seatMap == null) return null;

        List<Seat> seats = seatMap.getSeats();

        int totalSeats = seats != null ? seats.size() : 0;

        int availableSeats = seats != null ? (int) seats.stream()
                .filter(seat ->
                        Boolean.TRUE.equals(seat.getIsAvailable()) &&
                                Boolean.TRUE.equals(seat.getIsActive()) &&
                                !Boolean.TRUE.equals(seat.getIsBlocked()))
                .count() : 0;

        int windowSeats = seats != null ? (int) seats.stream()
                .filter(seat -> seat.getSeatType() != null &&
                        seat.getSeatType().name().contains("WINDOW"))
                .count() : 0;

        int aisleSeats = seats != null ? (int) seats.stream()
                .filter(seat -> seat.getSeatType() != null &&
                        seat.getSeatType().name().contains("AISLE"))
                .count() : 0;

        int middleSeats = seats != null ? (int) seats.stream()
                .filter(seat -> seat.getSeatType() != null &&
                        seat.getSeatType().name().contains("MIDDLE"))
                .count() : 0;

        int premiumSeats = seats != null ? (int) seats.stream()
                .filter(seat ->
                        Boolean.TRUE.equals(seat.getHasExtraLegRoom()) ||
                                Boolean.TRUE.equals(seat.getIsEmergencyExist()) ||
                                Boolean.TRUE.equals(seat.getHasExtraWidth()))
                .count() : 0;

        int emergencyExitSeats = seats != null ? (int) seats.stream()
                .filter(seat -> Boolean.TRUE.equals(seat.getIsEmergencyExist()))
                .count() : 0;

        return SeatMapResponse.builder()
                .id(seatMap.getId())
                .name(seatMap.getName())
                .totalRows(seatMap.getTotalRows())
                .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
                .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
                .airlineId(seatMap.getAirlineId())
                .cabinClassId(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getId() : null)
                .cabinClassName(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getName().toString() : null)
                .cabinClassCode(seatMap.getCabinClass() != null ? seatMap.getCabinClass().getCode() : null)
                .totalSeats(totalSeats)
                .availableSeats(availableSeats)
                .occupiedSeats(totalSeats - availableSeats)
                .windowSeats(windowSeats)
                .aisleSeats(aisleSeats)
                .middleSeats(middleSeats)
                .premiumSeats(premiumSeats)
                .emergencyExitSeats(emergencyExitSeats)
                .seats(seats != null
                        ? seats.stream().map(SeatMapper::toResponse).toList()
                        : null)
                .build();
    }

    public static SeatMapResponse toSimpleResponse(SeatMap seatMap) {
        return SeatMapResponse.builder()
                .totalRows(seatMap.getTotalRows())
                .leftSeatsPerRow(seatMap.getLeftSeatsPerRow())
                .rightSeatsPerRow(seatMap.getRightSeatsPerRow())
                .build();
    }
}
