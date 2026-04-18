package com.FlyNova.Flight_ops_Service.Controller;
import com.FlyNova.Flight_ops_Service.Service.FlightService;
import com.FlyNova.enums.FlightStatus;
import com.FlyNova.payload.request.FlightRequest;
import com.FlyNova.payload.response.FlightResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponse> CreateFlight(
            @Valid @RequestBody FlightRequest flightRequest,
            @RequestHeader("AirlineId") Long UserId
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightService.createFlight(UserId,flightRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/airline")
    public ResponseEntity<Page<FlightResponse>> getFlightByAirline(
            @RequestHeader("Airline-Id") Long airlineId,
            @RequestHeader(required = false) Long DepartureAiportId,
            @RequestHeader(required = false) Long ArrivalAirportId,
            Pageable pageable
    )
    {
        return ResponseEntity.ok(flightService.getFlightByAirline(
                airlineId,
                DepartureAiportId,
                ArrivalAirportId,
                pageable
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight
            (@PathVariable Long id,
             @Valid @RequestBody FlightRequest request) throws Exception {
        return ResponseEntity.ok(flightService.updateFlight(id,request));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<FlightResponse> ChangeStatus
            (@PathVariable Long id,
             @RequestParam FlightStatus status) throws Exception {
        return ResponseEntity.ok(flightService.ChangeStatus(id,status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id,@RequestHeader("Airline-Id")Long airlineId) throws Exception {
        flightService.deleteFlight(airlineId,id);
        return ResponseEntity.noContent().build();
    }
}
