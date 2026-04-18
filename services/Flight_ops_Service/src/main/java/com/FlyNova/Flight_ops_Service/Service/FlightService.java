package com.FlyNova.Flight_ops_Service.Service;

import com.FlyNova.enums.FlightStatus;
import com.FlyNova.payload.request.FlightRequest;
import com.FlyNova.payload.response.FlightResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FlightService {
    FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception;
    Page<FlightResponse> getFlightByAirline(Long airlineId,
                                            Long DepartureAiportId,
                                            Long ArrivalAirportId,
                                            Pageable pageable);
    FlightResponse getFlightById(Long id) throws Exception;
    FlightResponse updateFlight(Long id,FlightRequest flightRequest) throws Exception;
    FlightResponse ChangeStatus(Long id, FlightStatus status) throws Exception;
    void deleteFlight(Long airlineId,Long id) throws Exception;


}
