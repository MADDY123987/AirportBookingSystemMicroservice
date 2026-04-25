package com.FlyNova.payload.request;


import com.FlyNova.enums.SeatType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatRequest {
    @NotBlank(message = "Seat Number is Required")
    private String seatNumber;

    @NotNull(message = "Seat Row is Required")
    private Integer seatRow;

    private Long cabinClassId;

    private Character columnLetter;

    @NotNull(message = "Seat type is Required")
    private SeatType seatType;

    @NotNull(message = "Seat Map is Required")
    private Long seatMapId;

    private Boolean isAvailable;
    private Boolean isBlocked;
    private Boolean isEmergencyExist;
    private Boolean isActive;

    private Double basePrice;
    private Double premiumSuperCharge;

    private Boolean hasExtraLegRoom;
    private Boolean hasPowerOutlet;
    private Boolean hasTvScreen;
    private Boolean hasExtraWidth;

    private Integer seatPitch;
    private Integer seatWidth;


}
