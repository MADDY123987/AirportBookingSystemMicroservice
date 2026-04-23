package com.FlyNova.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaggagePolicyResponse {
    private Long id;
    private String name;
    private String description;

    //Cabin Baggage
    private Double cabinBaggageMaxWeight;
    private Integer cabinBaggagePieces;
    private Double cabinBaggageWeightPerPiece;
    private Double cabinBaggageMaxDimension;

    //Check-in baggage
    private Double checkInBaggageMaxWeight;
    private Integer checkInBaggagePieces;
    private Double checkInBaggageWeightPerPiece;
    private Integer freeCheckedBagesAllowance;

    //Benefits
    private Boolean priorityBaggage;
    private Boolean extraBaggageAllowance;

    // Relationships
    private Long airlineId;
    private Long fareId;

    // Audit
    private Instant createdAt;
    private Instant updatedAt;
}
