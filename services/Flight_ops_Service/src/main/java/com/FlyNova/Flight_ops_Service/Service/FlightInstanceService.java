package com.FlyNova.Flight_ops_Service.Service;

import com.FlyNova.payload.request.FlightInstanceRequest;
import com.FlyNova.payload.request.FlightRequest;
import com.FlyNova.payload.response.FlightInstanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface FlightInstanceService {
    FlightInstanceResponse CreateFlightInstance(Long airlineId, FlightInstanceRequest request) throws Exception;

    FlightInstanceResponse getFlightInstanceById(Long id) throws Exception;

    Page<FlightInstanceResponse> getByAirlineId(Long AirlineId,
                                                Long departureAirportId,
                                                Long arrivalAirportId,
                                                Long flightId,
                                                LocalDate onDate,
                                                Pageable pageable);
    FlightInstanceResponse UpdateFlightInstanceById(Long id, FlightInstanceRequest request) throws Exception;
    void deleteFlightInstance(Long id) throws Exception;
}
