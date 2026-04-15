package com.FlyNova.payload.request;

import com.FlyNova.enums.AirlineStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class AirlineRequest {

    @NotBlank
    @Size(min = 2,max = 3,message = "IataCode must be Exactly 2 Characters")
    private String iataCode;

    @NotBlank
    @Size(min = 3,max = 3,message = "ICAO must be Exactly 3 Characters")
    private String icaoCode;

    @NotBlank
    private String Name;

    private String alias;

    @NotBlank
    private String Country;
    private String logoUrl;

    private String website;

    private AirlineStatus status;

    private String alliance;

    private Long headquartersCityId;

    private String supportEmail;
    private String supportPhone;
    private String supportHours;

}
