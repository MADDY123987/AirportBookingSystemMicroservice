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
    private Double cabinBaggageWeight;

    @PositiveOrZero
    private Integer cabinBaggagePieces=1;

    @PositiveOrZero
    private Double cabinBaggageWeightPerPiece;

    @PositiveOrZero
    private Double cabinBaggageMaxDimension;

    @PositiveOrZero
    private Double checkInBaggageMaxWeight;

    @PositiveOrZero
    private Integer checkInBaggagePieces=1;

    @PositiveOrZero
    private Double checkInBaggageWeightPerPiece;

    @PositiveOrZero
    private Integer freeCheckBagesAllowance=0;

    private Boolean priorityBaggage;

    private Boolean extraBaggageAllowance;

}
