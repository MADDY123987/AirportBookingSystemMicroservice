package com.FlyNova.payload.response;

import com.FlyNova.enums.CabinClassType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CabinClassResponse {
    private Long id;
    private String name;
    private String code;
    private String description;
    private Long aircraftId;
    private Integer displayOrder;
    private Boolean isActive;
    private Boolean isBookable;
    private Integer typicalSeatPitch;
    private Integer typicalSeatWidth;
    private String seatType;
    private Instant createdAt;
    private Instant updatedAt;
    private SeatMapResponse seatMap;
}
