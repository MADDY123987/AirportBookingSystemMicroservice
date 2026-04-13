package com.FlyNova.payload.request;


import com.FlyNova.embeddable.Address;
import com.FlyNova.embeddable.Geocode;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportRequest {

    @NotBlank(message = "IATA code is Mandatory")
    @Size(min=3,max = 3,message = "IATA code must be exactly 3 characters")
    private String iataCode;

    @NotBlank(message = "Airport name is Mandatory")
    private String name;

    private ZoneId timeZone;

    @Valid
    private Address address;

    @NotNull(message = "City ID is Mandatory")
    private Long cityId;

    @Valid
    private Geocode geocode;

}
