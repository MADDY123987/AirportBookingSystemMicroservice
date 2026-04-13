package com.FlyNova.payload.response;

import com.FlyNova.embeddable.Address;
import com.FlyNova.embeddable.Geocode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZoneId;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirportResponse {
    private Long id;
    private String iataCode;
    private String name;
    private String detailedName;
    private ZoneId timeZone;
    private Address address;
    private CityResponse city;
    private Geocode geocode;
}
