package com.FlyNova.payload.request;

import com.FlyNova.enums.AirCraftStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftRequest {
    @NotBlank(message = "AirCraft code is Null")
    private String code;

    @NotBlank(message = "AirCraft Code is Required")
    private String model;

    @NotBlank(message = "Manufacturer is Required")
    private String manufacturer;

    @NotNull(message = "Seating Capacity is Required")
    @Positive(message = "SeatingCapacity should be Postive")
    private Integer seatingCapacity;

    @Positive(message = "EconomySeats must be Positive")
    private Integer economySeats;

    @Positive(message = "PremiumSeat must be Positive")
    private Integer premuimSeats;

    private Integer YearOfManufacture;


    @Positive(message = "Buiness Seats must be Positive")
    private Integer buinessSeats;

    @Positive(message = "First Class Seat must be Positive")
    private Integer firstClassSeats;

    @Positive(message = "Range must be Positive")
    private Integer rangeKm;

    @Positive(message = "Cruising Speed Must be Positive")
    private Integer cruisingSpeedKmh;

    @Positive(message = "Year of Manufacture must be Positive")
    private Integer maxAltitudeFt;

    private LocalDate registrationDate;
    private LocalDate nextMaintenanceDate;

    @NotNull(message = "Status is Required")
    private AirCraftStatus status;

    @NotNull(message = "Availability status is Required")
    private Boolean isAvailable;


    private Long currentAirportId;

}
