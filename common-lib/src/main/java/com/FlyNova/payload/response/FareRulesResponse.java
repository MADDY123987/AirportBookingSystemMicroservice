package com.FlyNova.payload.response;

import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FareRulesResponse {
    private Long id;
    private String ruleName;
    private Long fareId;
    private Long airlineId;
    private Boolean isRefundable;
    private Double changeFee;
    private Double cancellationFee;
    private Integer refundDeadLineDays;
    private Integer ChangeDeadlineHours;
    private Boolean isChangeable;
    private Instant createdAt;
    private Instant UpdatedAt;
}
