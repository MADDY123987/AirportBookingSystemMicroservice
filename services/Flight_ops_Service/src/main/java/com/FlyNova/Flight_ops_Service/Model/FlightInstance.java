package com.FlyNova.Flight_ops_Service.Model;


import com.FlyNova.enums.FlightStatus;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class FlightInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Long AirlineId;

    @ManyToOne
    private Flight flight;

    @Column(nullable = false)
    private Long DepartureAirportId;

    @Column(nullable = false)
    private Long ArrivalAirportId;

    @Column(nullable = false)
    private Long SceduleId;

    private LocalDateTime departureDateTime;

    private LocalDateTime arrivalDateTime;

    @Column(nullable = false)
    private Integer totalSeats;

    @Column(nullable = false)
    private Integer AvailableSeats;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    private Integer minAdvancedBookingDays;
    private Integer maxAdvancedBookingDays;

    private Boolean isActive=true;

    @Transient
    public String getFormatedDuration()
    {
        if(departureDateTime==null||arrivalDateTime==null) {
            return null;
        }
        Duration duration=Duration.between(
                departureDateTime,arrivalDateTime
        );
        long hours=duration.toHours();
        long minutes=duration.toMinutes();
        StringBuilder sb=new StringBuilder();

        if(hours>0) sb.append(hours).append("h ");
        if(minutes>0) sb.append(minutes).append("min");
        return sb.toString().trim();
    }
}
