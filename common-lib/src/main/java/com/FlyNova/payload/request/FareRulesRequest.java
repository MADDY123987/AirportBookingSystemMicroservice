package com.FlyNova.payload.request;


import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FareRulesRequest {
    @NotBlank(message = "Rule name is Required")
    private String ruleName;
    @NotNull(message = "Fare ID is Required")
    private Long fareId;

    private Long airlineId;

    private Boolean isRefundable;

    @PositiveOrZero(message = "Change Fee must be Positive or Zero")
    private Double changeFee;

    @PositiveOrZero(message = "Cancellation Fee must be Positive or Zero")
    private Double cancellationFee;

    @PositiveOrZero(message = "Refund Fee must be Positive or Zero")
    private Integer refundDeadLineDays;

    @PositiveOrZero(message = "Change deadLine hours must be positive or zero")
    private Integer ChangeDeadlineHours;

    private Boolean isChangeable;
}
