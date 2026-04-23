package com.FlyNova.Pricing_Service.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "baggage_policies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class BaggagePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "fare_id", nullable = false)
    private Fare fare;

    @Column(nullable = false)
    private String name;

    private String description;

    private Double cabinBaggageWeight;
    private Integer cabinBaggagePieces=1;

    private Double cabinBaggageWeightPerPiece;
    private Double cabinBaggageMaxDimension;

    private Double checkInBaggageMaxWeight;
    private Integer checkInBaggagePieces=1;

    private Double checkInBaggageWeightPerPiece;

    private Integer freeCheckBagesAllowance=0;

    private Boolean priorityBaggage=false;

    private Boolean extraBaggageAllowance=false;

    private Long airlineId;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
