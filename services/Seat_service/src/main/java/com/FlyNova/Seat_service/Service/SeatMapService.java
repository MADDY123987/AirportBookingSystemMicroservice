package com.FlyNova.Seat_service.Service;

import com.FlyNova.payload.request.SeatMapRequest;
import com.FlyNova.payload.response.SeatMapResponse;

public interface SeatMapService {
    SeatMapResponse CreateSeatMap(Long airlinerId,SeatMapRequest request) throws Exception;
    SeatMapResponse getSeatMapById(Long id) throws Exception;
    SeatMapResponse getSeatMapByCabinClass(Long cabinId);
    SeatMapResponse updateSeatMap(Long id, SeatMapRequest request) throws Exception;
    void deleteSeatMap(Long id) throws Exception;
}
