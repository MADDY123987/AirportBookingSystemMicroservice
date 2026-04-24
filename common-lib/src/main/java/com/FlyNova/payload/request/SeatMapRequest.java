package com.FlyNova.payload.request;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatMapRequest {

    @NotBlank(message = "Seat Map Name is Required")
    private String name;

    @Positive
    @NotNull(message = "Total Row is Required")
    private Integer totalRows;

    @Positive
    @NotNull(message = "Right Seats per Row is Required")
    private int rightSeatsPerRow;

    @Positive
    @NotNull(message = "Left Seats per Row is Required")
    private int leftSeatsPerRow;


    private Long CabinClassId;

}
