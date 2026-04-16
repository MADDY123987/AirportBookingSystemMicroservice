package com.FlyNova.Airline_Core_Service.Model;


import com.FlyNova.enums.AirCraftStatus;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String code;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String manufacturer;

    @Column(nullable = false)
    private Integer seatingCapacity;

    @Column(name= "Economy_Seats")
    private Integer economySeats=0;

    @Column(name = "Premuim_Seats")
    private Integer PremiumEconomySeats=0;

    @Column(name = "Buisness_Seats")
    private Integer buinessSeats=0;

    @Column(name = "First_class_Seats")
    private Integer firstClassSeats=0;

    private Integer rangeKm;

    @Column(name = "cruising_speed")
    private Integer cruisingSpeedKmh;

    private Integer maxAltitudeFt;

    @Column(name = "year_of_Manufacture")
    private Integer YearOfManufacture;

    private LocalDate registeredDate;
    private LocalDate nextMaintenanceDate;


    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false,length = 20)
    private AirCraftStatus status=AirCraftStatus.ACTIVE;

    private boolean isAvailable=true;

    @ManyToOne
    private Airline airline;

    private Long currentAirportId;

    @CreatedDate
    @Column(name = "createdAt",updatable = false,nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt",nullable = false)
    private Instant updatedAt;

    public Integer getTotalSeats()
    {
        return economySeats+PremiumEconomySeats+buinessSeats+firstClassSeats;
    }

    public boolean isOperational()
    {
        return AirCraftStatus.ACTIVE.equals(status)
                && Boolean.TRUE.equals(isAvailable);
    }

    public boolean requiredMaintenance()
    {
        return nextMaintenanceDate!=null
                && nextMaintenanceDate.isBefore(LocalDate.now().plusWeeks(2));
    }
}
