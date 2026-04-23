package com.FlyNova.Pricing_Service.Controller;

import com.FlyNova.Pricing_Service.Service.FareService;
import com.FlyNova.payload.request.FareRequest;
import com.FlyNova.payload.response.ApiResponse;
import com.FlyNova.payload.response.FareResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/fares")
public class FareController {

    private final FareService fareService;
    @PostMapping
    public ResponseEntity<FareResponse> Createfare
            (@Valid @RequestBody FareRequest request) throws Exception
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fareService.createFare(request));
    }
    @GetMapping
    public ResponseEntity<?>getFares()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(fareService.getFares());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FareResponse> getFareById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(fareService.getFareById(id));
    }

    @GetMapping("/flight/{flightId}/cabin-class/{cabinClassId}")
    public ResponseEntity<List<FareResponse>> getFaresByFlightAndCabinClass(
            @PathVariable Long flightId,
            @PathVariable Long cabinClassId
    )
    {
        return ResponseEntity.ok(fareService.getFareByFlightIdAndCabinClassId(flightId,cabinClassId));
    }

    @PostMapping("/batch_by_ids")
    public ResponseEntity<Map<Long,FareResponse>> getFaresByIds(@RequestBody List<Long>ids)
    {
        return ResponseEntity.ok(fareService.getFaresByIds(ids));
    }
    @PostMapping("/search")
    public ResponseEntity<Map<Long,FareResponse>> getLowestFarePerFlight
            (
                    @RequestBody List<Long> flightIds,
                    @RequestParam Long cabinClassId
            )
    {
        Map<Long,FareResponse>res=fareService.getLowestFarePerFlight(flightIds,cabinClassId);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FareResponse>updateFare
            (
                    @PathVariable Long id,
                    @Valid @RequestBody FareRequest request
            ) throws Exception {
        return ResponseEntity.ok(fareService.updateFare(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>deleteFare(@PathVariable Long id) throws Exception {
        fareService.deleteFare(id);
        return ResponseEntity.noContent().build();
    }
}
