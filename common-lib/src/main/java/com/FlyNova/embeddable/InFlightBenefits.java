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
public class InFlightBenefits {
    @Column(name = "complimentary_meals",nullable = false)
    @Builder.Default
    private Boolean complimentaryMeals=false;

    @Column(name = "premuim_meal_choice",nullable = false)
    @Builder.Default
    private Boolean premuimMealChoice=false;

    @Column(name = "in_Flight_Internet",nullable = false)
    @Builder.Default
    private Boolean inFlightInternet=false;

    @Column(name = "in_Flight_entertainment",nullable = false)
    @Builder.Default
    private Boolean inFlightEntertainment=false;

    @Column(name = "complimentary_beverages",nullable = false)
    @Builder.Default
    private Boolean complimentaryBeverages=false;

}
