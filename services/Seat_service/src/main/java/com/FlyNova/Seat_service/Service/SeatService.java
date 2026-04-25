package com.FlyNova.Seat_service.Service;

import com.FlyNova.Seat_service.Model.SeatMap;
import com.FlyNova.payload.request.SeatRequest;
import com.FlyNova.payload.response.SeatResponse;

import java.util.List;

public interface SeatService {
    void generateSeats(Long seatMapId) throws Exception;
    SeatResponse updateSeats(Long seatId, SeatRequest request);
    List<SeatResponse> getAll();

    SeatResponse getSeatById(Long id);
}
