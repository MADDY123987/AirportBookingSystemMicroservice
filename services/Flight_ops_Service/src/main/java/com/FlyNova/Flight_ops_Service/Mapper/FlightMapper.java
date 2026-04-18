package com.FlyNova.Flight_ops_Service.Mapper;

import com.FlyNova.Flight_ops_Service.Model.Flight;
import com.FlyNova.payload.request.FlightRequest;
import com.FlyNova.payload.response.AircraftResponse;
import com.FlyNova.payload.response.AirlineResponse;
import com.FlyNova.payload.response.AirportResponse;
import com.FlyNova.payload.response.FlightResponse;

public class FlightMapper {
    public static Flight toEntity(FlightRequest request)
    {
        if(request==null) return null;
        return Flight.builder()
                .flightNumber(request.getFlightNumber())
                .AirCraftId(request.getAirCraftId())
                .DepartureAirportId(request.getDepartureAirportId())
                .ArrivalAirportId(request.getArrivalAirportId())
                .status(request.getStatus())
                .build();
    }
    public static FlightResponse toResponse(Flight flight,
                                            AircraftResponse aircraft,
                                            AirlineResponse airlineResponse,
                                            AirportResponse departureAirport,
                                            AirportResponse ArrivalAirport
                                            )
    {
        if(flight==null) return null;
        return FlightResponse.builder()
                .id(flight.getId())
                .flightNumber(flight.getFlightNumber())
                .airline(airlineResponse)
                .aircraft(aircraft)
                .departureAirport(departureAirport)
                .arrivalAirport(ArrivalAirport)
                .status(flight.getStatus())
                .createdAt(flight.getCreatedAt())
                .updatedAt(flight.getUpdatedAt())
                .build();
    }
    public static void updateEntity(FlightRequest request,Flight existing)
    {
        if(request==null || existing==null) return;
        if(request.getFlightNumber() !=null) existing.setFlightNumber(request.getFlightNumber());
        if(request.getAirlineId() !=null) existing.setAirlineId(request.getAirlineId());
        if(request.getAirCraftId() !=null) existing.setAirCraftId(request.getAirCraftId());
        if(request.getDepartureAirportId()!=null) existing.setDepartureAirportId(request.getDepartureAirportId());
        if(request.getArrivalAirportId()!=null) existing.setArrivalAirportId(request.getArrivalAirportId());
        if(request.getStatus() !=null) existing.setStatus(request.getStatus());
    }
}
