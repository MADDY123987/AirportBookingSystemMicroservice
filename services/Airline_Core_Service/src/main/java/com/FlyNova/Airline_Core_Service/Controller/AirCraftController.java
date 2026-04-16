package com.FlyNova.Airline_Core_Service.Controller;

import com.FlyNova.Airline_Core_Service.Service.AirCraftService;
import com.FlyNova.payload.request.AircraftRequest;
import com.FlyNova.payload.response.AircraftResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aircrafts")
public class AirCraftController {

    private final AirCraftService airCraftService;

    @PostMapping
    public ResponseEntity<AircraftResponse> CreateAirCraft(
            @RequestBody @Valid AircraftRequest aircraftRequest,
            @RequestHeader("X-User-Id") Long userId
            ) throws Exception {
        AircraftResponse airCraft=airCraftService.createAirCraft(aircraftRequest,userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                airCraft
        );
    }
    @GetMapping("{id}")
    public ResponseEntity<AircraftResponse>getAirCraftById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airCraftService.getAirCraftById(id));
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponse>> listAllAirCrafts
            (
                    @RequestHeader("X-User-Id")Long UserId
            ) throws Exception {
        return ResponseEntity.ok(airCraftService.listAllAirCraftByOwner(UserId));
    }

    @PutMapping("{id}")
    public ResponseEntity<AircraftResponse> updatedAirCraft
            (
                    @PathVariable Long id,
                    @RequestBody AircraftRequest request,
                    @RequestHeader("X-user-Id") Long UserId
            ) throws Exception {
        return ResponseEntity.ok(airCraftService.updateAirCraft(id,request,UserId));
    }
    @DeleteMapping
    public ResponseEntity<Void> deleteAirCraft
            (
                    @PathVariable Long id,
                    @RequestHeader("X-user-id") Long UserId
            ) throws Exception {
        airCraftService.deleteAirCraft(id,UserId);
        return ResponseEntity.noContent().build();
    }

}
