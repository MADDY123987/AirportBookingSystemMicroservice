package com.FlyNova.payload.request;


import com.FlyNova.enums.CabinClassType;
import com.FlyNova.payload.response.SeatMapResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CabinClassRequest {
    @NotBlank(message = "Name is Required")
    private CabinClassType name;
    @NotBlank(message = "Code is Required")
    private String code;

    private String description;

    @NotNull(message = "aircraftId is Required")
    private Long aircraftId;

    private Integer displayOrder;
    private Boolean isActive;
    private Boolean isBookable;
    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;
    private String seatType;
}
