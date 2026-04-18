package com.FlyNova.Flight_ops_Service.Service;

import com.FlyNova.Flight_ops_Service.Model.FlightSchedule;
import com.FlyNova.payload.request.FlightScheduleRequest;
import com.FlyNova.payload.response.FlightScheduleResponse;

import java.util.List;

public interface FlightScheduleService {
    FlightScheduleResponse createFlightSchedule(Long AirlineId,
                                                FlightScheduleRequest request) throws Exception;
    FlightScheduleResponse getFlightScheduleById(Long id) throws Exception;
    List<FlightScheduleResponse> getFlightScheduleByAirline(Long UserId);
    FlightScheduleResponse updateFlightSchedule(Long id,FlightScheduleRequest request) throws Exception;
    void deleteFlightSchedule(Long id) throws Exception;
}
