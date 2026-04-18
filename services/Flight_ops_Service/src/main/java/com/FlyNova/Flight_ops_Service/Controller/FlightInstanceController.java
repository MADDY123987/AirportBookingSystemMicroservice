package com.FlyNova.Flight_ops_Service.Controller;

import com.FlyNova.Flight_ops_Service.Model.FlightInstance;
import com.FlyNova.Flight_ops_Service.Service.FlightInstanceService;
import com.FlyNova.payload.request.FlightInstanceRequest;
import com.FlyNova.payload.response.ApiResponse;
import com.FlyNova.payload.response.FlightInstanceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flight-instances")
public class FlightInstanceController {

    private final FlightInstanceService flightInstanceService;

    @PostMapping
    public ResponseEntity<FlightInstanceResponse> createFlightInstance
            (
                    @RequestHeader("X-Airline-Id") Long airlineId,
                    @Valid @RequestBody FlightInstanceRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightInstanceService.CreateFlightInstance(airlineId, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightInstanceResponse> getFlightInstanceById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(flightInstanceService.getFlightInstanceById(id));
    }

    @GetMapping
    public ResponseEntity<Page<FlightInstanceResponse>> getByAirlineId(
            @RequestHeader("X-UserId") Long UserId,
            @RequestParam(required = false) Long departureAirportId,
            @RequestParam(required = false) Long arrivalAirportId,
            @RequestParam(required = false) Long flightId,
            @RequestParam(required = false) LocalDate onDate,
            Pageable pageable
    ) {
        return ResponseEntity.ok(flightInstanceService.getByAirlineId(
                UserId,
                departureAirportId,
                arrivalAirportId,
                flightId,
                onDate,
                pageable
        ));
    }

    @PutMapping("{/id}")
    public ResponseEntity<FlightInstanceResponse> updateFlightInstance
            (
                    @PathVariable Long id,
                    @RequestBody FlightInstanceRequest request
            ) throws Exception {
        return ResponseEntity.ok(flightInstanceService.UpdateFlightInstanceById(id, request));
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<ApiResponse> deleteFlightInstance(@PathVariable Long id) throws Exception {
        flightInstanceService.deleteFlightInstance(id);
        ApiResponse response=new ApiResponse("Flight Instance Deleted");
        return ResponseEntity.noContent().build();
    }


}
