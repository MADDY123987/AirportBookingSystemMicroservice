package com.FlyNova.Flight_ops_Service.Model;


import jakarta.persistence.*;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Flight flight;

    @Column(nullable = false)
    private Long departureAirportId;
    @Column(nullable = false)
    private Long arrivalAirportId;
    @Column(nullable = false)
    private LocalTime departureTime;
    @Column(nullable = false)
    private LocalTime arrivalTime;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate endDate;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> OperatingDays;

    private Boolean isActive=true;


}
