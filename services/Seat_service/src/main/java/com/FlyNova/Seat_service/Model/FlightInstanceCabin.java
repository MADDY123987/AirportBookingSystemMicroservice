package com.FlyNova.Seat_service.Model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class FlightInstanceCabin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    Long flightInstanceId;

    @ManyToOne
    private CabinClass cabinClass;

    @Column(nullable = false)
    private Integer totalSeats;

    private Integer BookedSeats=0;

    //todo:watch
    //
    // flightInstanceCabin

    @OneToMany(mappedBy = "flightInstanceCabin")
    private List<SeatInstance> seats=new ArrayList<>();

    public Integer getAvailableSeats(){
            return totalSeats-BookedSeats;
    }



}
