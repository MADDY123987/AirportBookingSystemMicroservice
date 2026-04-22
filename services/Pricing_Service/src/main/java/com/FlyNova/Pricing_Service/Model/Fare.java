package com.FlyNova.Pricing_Service.Model;


import com.FlyNova.embeddable.SeatBenefits;
import com.FlyNova.enums.cabinClassType;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Character rbdCode;

    @Column(nullable = false)
    private Long flightId;

    @Column(nullable = false)
    private Long cabinClassId;

    @Enumerated(EnumType.STRING)
    private cabinClassType cabinClass;

    @Column(nullable = false)
    private Double baseFare;

    private Double taxesAndFees;
    private Double airlineFees;

    @Column(nullable = false)
    private Double currentPrice;

    private String fareLabel;

    //todo:when create baggage Policy
   // private BagggagePolicy bagggagePolicy;

    //todo:when Create Fare Rules
    //private FareRoles roles;

    @Embedded
    private SeatBenefits seatBenefits=new SeatBenefits();

    @Embedded
    private BoardingBenefits boardingBenefits;
}
