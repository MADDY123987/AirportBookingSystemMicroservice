package com.FlyNova.Seat_service.Controller;
import com.FlyNova.Seat_service.Service.SeatMapService;
import com.FlyNova.payload.request.SeatMapRequest;
import com.FlyNova.payload.response.SeatMapResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/seat-maps")
@RequiredArgsConstructor
public class SeatMapController {

    private final SeatMapService seatMapService;

    @PostMapping
    public ResponseEntity<SeatMapResponse> createSeatMap(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody SeatMapRequest request) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(seatMapService.CreateSeatMap(userId, request));
    }


    @GetMapping("/{id}")
    public ResponseEntity<SeatMapResponse> getSeatMapById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(seatMapService.getSeatMapById(id));
    }


    @GetMapping("/cabin-class/{cabinClassId}")
    public ResponseEntity<SeatMapResponse> getSeatMapsByCabinClass(
            @PathVariable Long cabinClassId) {
        SeatMapResponse responses = seatMapService.getSeatMapByCabinClass(cabinClassId);
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeatMapResponse> updateSeatMap(
            @PathVariable Long id,
            @Valid @RequestBody SeatMapRequest request) throws Exception {
        return ResponseEntity.ok(seatMapService.updateSeatMap( id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSeatMap(@PathVariable Long id) throws Exception {
        seatMapService.deleteSeatMap(id);
        return ResponseEntity.ok("Seat map deleted");
    }
}

