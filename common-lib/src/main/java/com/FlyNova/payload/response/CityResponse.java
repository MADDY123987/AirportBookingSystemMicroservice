package com.FlyNova.payload.response;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {
    private Long id;
    private String name;
    private String cityCode;
    private String CountryCode;
    private String regionCode;
    private String CountryName;
    private  String timeZoneOffset;
}
