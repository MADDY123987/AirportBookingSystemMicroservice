package com.FlyNova.payload.response;


import com.FlyNova.enums.FlightStatus;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FlightResponse {

    private Long id;
    private String flightNumber;
    private AircraftResponse aircraft;
    private AirlineResponse airline;
    private AirportResponse arrivalAirport;
    private AirportResponse departureAirport;
    private LocalDateTime departureTime;
    private LocalDateTime ArrivalTime;
    private FlightStatus status;
    private  Double lowestPrice;
    private Integer totalAvailableSeats;
    private Instant createdAt;
    private Instant updatedAt;

}
