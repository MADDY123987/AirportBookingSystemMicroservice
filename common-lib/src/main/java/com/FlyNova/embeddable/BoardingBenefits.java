package com.FlyNova.embeddable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardingBenefits {

    @Column(name = "priority_boarding",nullable = false)
    @Builder.Default
    private Boolean priorityBoarding=false;

    @Column(name = "priority_checking",nullable = false)
    @Builder.Default
    private Boolean priorityCheckin=false;

    @Column(name = "fast_track_security",nullable = false)
    private Boolean fastTrackSecurity=false;
}
