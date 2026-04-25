package com.FlyNova.Seat_service.Model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatMap {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private int totalRows;

    @Column(nullable = false)
    private int rightSeatsPerRow;

    @Column(nullable = false)
    private int leftSeatsPerRow;

    @Column(name="airline_Id",nullable = false)
    private Long airlineId;

    //todo:watch
    @OneToMany(mappedBy="seatMap"
            ,fetch = FetchType.LAZY
            ,cascade = CascadeType.ALL
            ,orphanRemoval = true)
    private List<Seat>seats;

    @OneToOne
    private CabinClass cabinClass;


}
