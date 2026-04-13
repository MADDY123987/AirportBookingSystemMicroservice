package com.FlyNova.location_service.Model;

import com.FlyNova.embeddable.Address;
import com.FlyNova.embeddable.Geocode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,nullable = false,length = 3)
    private String iataCode;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Embedded
    private Geocode geocode;

    @Column(name="time_zone_id",length = 50)
    private ZoneId timeZone;

    @ManyToOne
    @JsonIgnore
    private City city;

    @JsonIgnore
    @Transient
    public  String getDetailedName()
    {
        if(city!=null && city.getCountryCode()!=null)
        {
            return name.toUpperCase() +"/"+ city.getCityCode();
        }
        return name.toUpperCase();
    }

}
