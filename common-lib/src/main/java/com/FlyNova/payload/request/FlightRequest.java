package com.FlyNova.payload.request;


import com.FlyNova.enums.FlightStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightRequest {

    @NotBlank(message = "Flight Number is Required")
    @Size(max = 10)
    private String flightNumber;

    private Long AirlineId;

    @NotNull(message = "AirCraftId is Required")
    private Long AirCraftId;

    @NotNull(message = "Departure AirportId is Required")
    private Long DepartureAirportId;

    @NotNull(message = "Arrival AirportId is Required")
    private Long ArrivalAirportId;

    private FlightStatus status;
}
