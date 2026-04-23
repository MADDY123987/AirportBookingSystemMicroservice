package com.FlyNova.Pricing_Service.Model;
import com.FlyNova.embeddable.*;
import com.FlyNova.enums.cabinClassType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(AuditingEntityListener.class)
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

    @Column(length = 100)
    private String fareLabel;

    //todo:when create baggage Policy
    @OneToOne(mappedBy = "fare")
   private BaggagePolicy bagggagePolicy;

    //todo:when Create Fare Rules
    @OneToOne(mappedBy = "fare")
    private FareRules fareRules;

    @Embedded
    private SeatBenefits seatBenefits=new SeatBenefits();

    @Embedded
    private BoardingBenefits boardingBenefits=new BoardingBenefits();

    @Embedded
    @Builder.Default
    private InFlightBenefits inFlightBenefits=new InFlightBenefits();

    @Embedded
    @Builder.Default
    private FlexibilityBenefits flexibilityBenefits=new FlexibilityBenefits();

    @Embedded
    @Builder.Default
    private PremuimServiceBenefits premuimServiceBenefits=new PremuimServiceBenefits();

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public Double getTotalPrice()
    {
        return baseFare+taxesAndFees+airlineFees+currentPrice;
    }


}
