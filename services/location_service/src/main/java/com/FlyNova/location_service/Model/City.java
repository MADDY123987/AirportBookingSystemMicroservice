package com.FlyNova.location_service.Model;


import com.FlyNova.payload.response.CityResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String cityCode;

    @Column(nullable = false)
    private String countryCode;
    @Column(nullable = false)
    private String countryName;

    @Column(nullable = false)
    private String regionCode;

    @Column(name="time_zone_id",length = 50)
    private  String timeZoneId;

}
