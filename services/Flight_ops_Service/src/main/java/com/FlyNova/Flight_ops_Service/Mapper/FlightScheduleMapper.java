package com.FlyNova.Flight_ops_Service.Mapper;

import com.FlyNova.Flight_ops_Service.Model.Flight;
import com.FlyNova.Flight_ops_Service.Model.FlightSchedule;
import com.FlyNova.payload.request.FlightScheduleRequest;
import com.FlyNova.payload.response.AirportResponse;
import com.FlyNova.payload.response.FlightScheduleResponse;

public class FlightScheduleMapper {
    public static FlightSchedule toEntity(FlightScheduleRequest request,Flight flight)
    {
        if(request==null) return null;
        return FlightSchedule.builder()
                .flight(flight)
                .departureAirportId(request.getDepartureAirportId()!=null?
                        request.getDepartureAirportId() : flight.getDepartureAirportId())
                .arrivalAirportId(request.getArrivalAirportId()!=null?
                        request.getArrivalAirportId(): flight.getArrivalAirportId())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .OperatingDays(request.getOperatingDays())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .isActive(request.getIsActive())
                .build();
    }
    public static FlightScheduleResponse toResponse(FlightSchedule fi,
                                                    AirportResponse arrival,
                                                    AirportResponse departure)
    {
        if(fi==null) return null;
        return FlightScheduleResponse.builder()
                .id(fi.getId())
                .FlightId(fi.getFlight()!=null?fi.getFlight().getId():null)
                .FlightNumber(fi.getFlight()!=null?fi.getFlight().getFlightNumber():null)
                .departureAirport(departure)
                .arrivalAirport(arrival)
                .departureTime(fi.getDepartureTime())
                .arrivalTime(fi.getArrivalTime())
                .startDate(fi.getStartDate())
                .endDate(fi.getEndDate())
                .operatingDays(fi.getOperatingDays())
                .isActive(fi.getIsActive())
                .build();
    }
    public static void updateEntity(FlightScheduleRequest request,FlightSchedule existing)
    {
        if(request==null||existing==null) return;
        if(request.getDepartureAirportId()!=null)
        {
            existing.setDepartureAirportId(request.getDepartureAirportId());
        }
        if(request.getArrivalAirportId()!=null)
        {
            existing.setArrivalAirportId(request.getArrivalAirportId());
        }
        if(request.getDepartureTime()!=null)
        {
            existing.setDepartureTime(request.getDepartureTime());
        }
        if(request.getArrivalTime()!=null)
        {
            existing.setArrivalTime(request.getArrivalTime());
        }
        if (request.getStartDate()!=null)
        {
            existing.setStartDate(request.getStartDate());
        }
        if(request.getEndDate()!=null)
        {
            existing.setEndDate(request.getEndDate());
        }
        if(request.getOperatingDays()!=null)
        {
            existing.setOperatingDays(request.getOperatingDays());
        }
        if(request.getIsActive()!=null)
        {
            existing.setIsActive(request.getIsActive());
        }

    }
}
