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
public class PremuimServiceBenefits {

    @Column(name = "lounge_access",nullable = false)
    @Builder.Default
    private Boolean loungeAccess=false;

    @Column(name = "airport_transfer",nullable = false)
    @Builder.Default
    private Boolean airportTransfer=false;
}
