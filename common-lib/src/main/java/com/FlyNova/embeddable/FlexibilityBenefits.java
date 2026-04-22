package com.FlyNova.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FlexibilityBenefits {
    private Boolean freeDataChange=false;

    @Column(name = "partial_fund",nullable = false)
    @Builder.Default
    private Boolean partilalRefund=false;

    @Column(name = "full_refund",nullable = false)
    @Builder.Default
    private Boolean fullRefund=false;

}
