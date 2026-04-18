package com.FlyNova.payload.response;


import com.FlyNova.enums.FlightStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightInstanceResponse {

    private Long id;
    private Long flightId;
    private String flightNumber;

    private Long airlineId;
    private String airlineName;
    private String airlineLogo;

    private Long airCraftId;
    private String airCraftModel;
    private String airCraftCode;
    private AirportResponse departureAirport;
    private AirportResponse ArrivalAirport;

    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private String formattedDuration;

    private Integer totalSeats;
    private Integer availableSeats;

    private FlightStatus status;

    private Integer minAdvancedBookingDays;
    private Integer maxAdvancedBookingDays;
    private Boolean isActive;
}
