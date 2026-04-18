package com.FlyNova.Flight_ops_Service.Controller;


import com.FlyNova.Flight_ops_Service.Service.FlightScheduleService;
import com.FlyNova.payload.request.FlightScheduleRequest;
import com.FlyNova.payload.response.ApiResponse;
import com.FlyNova.payload.response.FlightScheduleResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/flight-schedules")
@RequiredArgsConstructor
public class FlightScheduleController {
    private final FlightScheduleService flightScheduleService;

    @PostMapping
    public ResponseEntity<FlightScheduleResponse> createFlightSchedule
            (
                    @RequestHeader("Airline-Id") Long airlineId,
                    @Valid @RequestBody FlightScheduleRequest request
                    ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightScheduleService.createFlightSchedule(
                        airlineId,request
                ));
    }

    @GetMapping("{/id}")
    public ResponseEntity<FlightScheduleResponse> getFlightScheduleById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(flightScheduleService.getFlightScheduleById(id));
    }

    @GetMapping
    public ResponseEntity<?> getFlightSchedules(
            @RequestHeader("X-Airline-Id") Long airlineId
    )
    {
        return ResponseEntity.ok(
                flightScheduleService.getFlightScheduleByAirline(airlineId)
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<FlightScheduleResponse> updateFlightSchedule(
            @PathVariable Long id,
            @Valid @RequestBody FlightScheduleRequest request) throws Exception {
        return ResponseEntity.ok(flightScheduleService.updateFlightSchedule(id,request));
    }
    @DeleteMapping("{/id}")
    public ResponseEntity<ApiResponse> deleteFlightSchedule(@PathVariable Long id) throws  Exception
    {
        flightScheduleService.deleteFlightSchedule(id);
        ApiResponse apiResponse=new ApiResponse("Flight Scedule removed Succesfully");
        return ResponseEntity.ok(apiResponse);
    }

}
