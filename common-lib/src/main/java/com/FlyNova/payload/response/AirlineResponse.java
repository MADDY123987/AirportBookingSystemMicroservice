package com.FlyNova.payload.response;

import com.FlyNova.embeddable.Support;
import com.FlyNova.enums.AirlineStatus;
import com.FlyNova.payload.dto.UserDTO;
import lombok.*;

import java.time.Instant;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AirlineResponse {
    private Long id;
    private String iataCode;

    private String name;
    private String icaoCode;

    private String alias;
    private String logoUrl;
    private String website;
    private AirlineStatus  status;
    private String alliance;

    private Instant createdAt;
    private Instant updatedAt;

    private Long ownerId;
    private UserDTO userDTO;
    private Long updatedById;

    private CityResponse cityResponse;
    private Support support;
}
