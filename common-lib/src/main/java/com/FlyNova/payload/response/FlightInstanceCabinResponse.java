package com.FlyNova.payload.response;
import com.FlyNova.enums.CabinClassType;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightInstanceCabinResponse {
    private Long id;
    private Long flightInstanceId;
    private CabinClassType cabinClassType;
    private CabinClassResponse cabinClass;
    @Builder.Default
    private List<SeatInstanceResponse> seats=new ArrayList<>();
    @Builder.Default
    private SeatMapResponse seatMap=new SeatMapResponse();
    private Integer totalSeats;
    private Integer BookedSeats;
    private Integer availableSeats;
    private Integer isActive;
    private Integer canBook;

}
