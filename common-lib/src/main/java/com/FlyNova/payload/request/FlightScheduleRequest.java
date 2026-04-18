package com.FlyNova.payload.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightScheduleRequest {

    @NotNull(message = "Flight Id is Required")
    private Long flightId;

    private Long departureAirportId;
    private Long arrivalAirportId;

    @NotNull(message = "Departure Time is Required")
    private LocalTime departureTime;

    @NotNull(message = "Arrival Time is Required")
    private LocalTime arrivalTime;

    @NotNull(message = "Start Date is Required")
    private LocalDate startDate;

    @NotNull(message = "End Date is Required")
    private LocalDate endDate;

    private List<DayOfWeek> OperatingDays;

    private Boolean isActive;


}
