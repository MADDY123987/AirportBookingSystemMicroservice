package com.FlyNova.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor

public class BaggagePolicyRequest {
    @NotBlank(message = "Policy Name is Required")
    private String name;

    @NotNull(message = "fare id is Required")
    private Long fareId;

    private String description;

    @PositiveOrZero
    private Double cabinBaggageMaxWeight;

    @PositiveOrZero
    private Integer cabinBaggagePieces;

    @PositiveOrZero
    private Double cabinBaggageWeightPerPiece;

    @PositiveOrZero
    private Double cabinBaggageMaxDimension;

    @PositiveOrZero
    private Double checkInBaggageMaxWeight;

    @PositiveOrZero
    private Integer checkInBaggagePieces;

    @PositiveOrZero
    private Double checkInBaggageWeightPerPiece;

    @PositiveOrZero
    private Integer freeCheckedBagesAllowance;

    //Benefits
    private Boolean priorityBaggage;
    private Boolean extraBaggageAllowance;

}
