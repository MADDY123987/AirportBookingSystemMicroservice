package com.FlyNova.Seat_service.Controller;

import com.FlyNova.Seat_service.Service.CabinClassService;
import com.FlyNova.enums.CabinClassType;
import com.FlyNova.payload.request.CabinClassRequest;
import com.FlyNova.payload.response.CabinClassResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.WhereJoinTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cabin-classes")
@RequiredArgsConstructor
public class CabinClassController {
    private final CabinClassService cabinClassService;

    @PostMapping
    public ResponseEntity<CabinClassResponse>createCabinClass
            (@Valid @RequestBody CabinClassRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        cabinClassService.createCabinClass(request)
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CabinClassResponse> getCabinClassById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(cabinClassService.getCabinClassById(id));
    }

    @GetMapping("/aircraft/{id}/name/{cabinClass}")
    public ResponseEntity<CabinClassResponse> getCabinClassesByAircraftIdAndName(
            @PathVariable CabinClassType cabinClass,
            @PathVariable Long id
            )
    {
        return ResponseEntity.ok(
                cabinClassService.getAirCraftIdAndName(
                        id,cabinClass
                ));
    }

    @GetMapping("/aircraft/{aircraftId}")
    public ResponseEntity<List<CabinClassResponse>> getCabinClassesByAircraftId(
            @PathVariable Long aircraftId) {
        return ResponseEntity.ok(cabinClassService.getCabinClassesByAircraftId(aircraftId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CabinClassResponse> updateCabinClass(
            @PathVariable Long id,
            @Valid @RequestBody CabinClassRequest request) throws Exception {
        return ResponseEntity.ok(cabinClassService.updateCabinClass(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCabinClass(@PathVariable Long id) throws Exception {
        cabinClassService.deleteCabinClass(id);
        return ResponseEntity.noContent().build();
    }
}
