package com.FlyNova.Flight_ops_Service.Mapper;


import com.FlyNova.Flight_ops_Service.Model.Flight;
import com.FlyNova.Flight_ops_Service.Model.FlightInstance;
import com.FlyNova.enums.FlightStatus;
import com.FlyNova.payload.request.FlightInstanceRequest;
import com.FlyNova.payload.request.FlightRequest;
import com.FlyNova.payload.response.*;

public class FlightInstanceMapper {

    public static FlightInstance toEntity(FlightInstanceRequest request, Flight flight)
    {
        if(flight==null) return null;
        return FlightInstance.builder()
                .flight(flight)
                .AirlineId(request.getAirlineId())
                .SceduleId(request.getScheduleId())
                .DepartureAirportId(request.getDepartureAirportId()!=null ?request.getDepartureAirportId(): flight.getDepartureAirportId())
                .ArrivalAirportId(request.getArrivalAirportId()!=null ? request.getArrivalAirportId():flight.getArrivalAirportId())
                .departureDateTime(request.getDepartureDateTime())
                .arrivalDateTime(request.getArrivalDateTime())
                .totalSeats(request.getTotalSeats())
                .AvailableSeats(request.getAvailableSeats()!=null?
                        request.getAvailableSeats() : request.getTotalSeats())
                .status(request.getStatus()!=null?request.getStatus():FlightStatus.SCHEDULED)
                .minAdvancedBookingDays(request.getMinAdvanceBookingDays())
                .maxAdvancedBookingDays(request.getMaxAdvanceBookingDays())
                .isActive(request.getIsActive()!=null ?
                        request.getIsActive():true)
                .build();
    }
    public static FlightInstanceResponse toResponse(FlightInstance instance,
                                                    AirlineResponse airline,
                                                    AircraftResponse aircraft,
                                                    AirportResponse departureAiport,
                                                    AirportResponse arivalAirport)
    {
        if(instance==null) return null;
        return FlightInstanceResponse.builder()
                .id(instance.getId())
                .flightId(instance.getFlight()!=null?instance.getFlight().getId() : null)
                .flightNumber(instance.getFlight()!=null?instance.getFlight().getFlightNumber():null)
                .airCraftId(instance.getFlight().getAirCraftId())
                .airCraftModel(aircraft.getModel())
                .airCraftCode(aircraft.getCode())
                .airlineId(instance.getAirlineId())
                .airlineName(airline.getName())
                .airlineLogo(airline.getLogoUrl())
                .departureAirport(departureAiport)
                .ArrivalAirport(arivalAirport)
                .departureDateTime(instance.getDepartureDateTime())
                .arrivalDateTime(instance.getArrivalDateTime())
                .formattedDuration(instance.getFormatedDuration())
                .totalSeats(instance.getTotalSeats())
                .availableSeats(instance.getAvailableSeats())
                .status(instance.getStatus())
                .minAdvancedBookingDays(instance.getMinAdvancedBookingDays())
                .maxAdvancedBookingDays(instance.getMaxAdvancedBookingDays())
                .build();
    }
    public static void toUpdateEntity(FlightInstanceRequest request, FlightInstance existing) {

        if (request == null || existing == null) return;

        if (request.getDepartureAirportId() != null) {
            existing.setDepartureAirportId(request.getDepartureAirportId());
        }

        if (request.getArrivalAirportId() != null) {
            existing.setArrivalAirportId(request.getArrivalAirportId());
        }

        if (request.getDepartureDateTime() != null) {
            existing.setDepartureDateTime(request.getDepartureDateTime());
        }

        if (request.getArrivalDateTime() != null) {
            existing.setArrivalDateTime(request.getArrivalDateTime());
        }
        if(request.getTotalSeats()!=null)
        {
            existing.setTotalSeats(request.getTotalSeats());
        }

        if (request.getAvailableSeats() != null) {
            existing.setAvailableSeats(request.getAvailableSeats());
        }

        if (request.getStatus() != null) {
            existing.setStatus(request.getStatus());
        }

        if (request.getMinAdvanceBookingDays() != null) {
            existing.setMinAdvancedBookingDays(request.getMinAdvanceBookingDays());
        }

        if (request.getMaxAdvanceBookingDays() != null) {
            existing.setMaxAdvancedBookingDays(request.getMaxAdvanceBookingDays());
        }

        if (request.getIsActive() != null) {
            existing.setIsActive(request.getIsActive());
        }
    }
}
