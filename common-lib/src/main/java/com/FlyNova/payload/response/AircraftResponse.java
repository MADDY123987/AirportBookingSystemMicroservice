package com.FlyNova.payload.response;


import com.FlyNova.enums.AirCraftStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AircraftResponse {
    private Long id;
    private String code;
    private String model;
    private String manufacturer;
    private Integer seatingCapacity;
    private Integer economySeats;
    private Integer PremiumEconomySeats;
    private Integer buinessSeats;
    private Integer firstClassSeats;
    private Integer rangeKm;
    private Integer cruisingSpeedKmh;
    private Integer maxAltitudeFt;
    private Integer YearOfManufacture;
    private LocalDate registeredDate;
    private LocalDate nextMaintenanceDate;
    private AirCraftStatus status;
    private boolean isAvailable;

    private Long airlineId;
    private String airlineName;
    private String airlineIataCode;

    private Long currentAirportId;
    private Long currentAirportCity;
    private Long currentAirportCode;
    private Long currentAirportName;

    private Integer totalSeats;
    private Boolean requiredMaintenance;
    private Boolean isOperational;

    private Instant createdAt;
    private Instant updatedAt;
}
