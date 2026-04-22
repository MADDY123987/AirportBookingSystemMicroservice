package com.FlyNova.embeddable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class SeatBenefits {
    private Boolean extraSeatSpace=false;
    private Boolean preferredSeatsChoice=false;
    private Boolean advanceSeatSelection=false;
    private Boolean guaranteedSeatTogether=false;
}
