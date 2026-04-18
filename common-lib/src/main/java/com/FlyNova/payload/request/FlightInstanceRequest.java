package com.FlyNova.payload.request;


import com.FlyNova.enums.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FlightInstanceRequest {
    @NotNull(message = "Flight Id is Required")
    private Long flightId;

    private Long AirlineId;

    private Long scheduleId;

    private Long departureAirportId;

    private Long ArrivalAirportId;

    @NotNull(message = "Departure DateTime is Required")
    private LocalDateTime DepartureDateTime;

    @NotNull(message = "Airport DateTime is Required")
    private LocalDateTime ArrivalDateTime;

    @NotNull(message = "Total Seats is Required")
    @Positive
    private Integer totalSeats;

    @PositiveOrZero
    private Integer availableSeats;


    private FlightStatus status;

    private Integer minAdvanceBookingDays;
    private Integer maxAdvanceBookingDays;
    private Boolean isActive;
}
