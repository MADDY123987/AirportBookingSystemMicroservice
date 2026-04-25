package com.FlyNova.Seat_service.Service;

import com.FlyNova.Seat_service.Model.FlightInstanceCabin;
import com.FlyNova.payload.request.FlightInstanceCabinRequest;
import com.FlyNova.payload.response.FlightInstanceCabinResponse;

public interface FlightInstanceCabinService {
    FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request);
}
