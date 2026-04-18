package com.FlyNova.Airline_Core_Service.Controller;


import com.FlyNova.Airline_Core_Service.Service.AirlineService;
import com.FlyNova.enums.AirlineStatus;
import com.FlyNova.payload.request.AirlineRequest;
import com.FlyNova.payload.response.AirlineDropDownItem;
import com.FlyNova.payload.response.AirlineResponse;
import com.FlyNova.payload.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;



    @PostMapping
    public ResponseEntity<AirlineResponse> createAirline(
            @Valid @RequestBody AirlineRequest airlineRequest,
            @RequestHeader("X-UserId")Long UserId)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                airlineService.createAirline(airlineRequest,UserId)
        );
    }

    @GetMapping("/admin")
    public ResponseEntity<AirlineResponse> getAirlineByOwner(
            @RequestHeader("X-UserId") Long UserId) throws Exception {
        return ResponseEntity.ok(airlineService.getAirlineByOwner(UserId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirlineResponse> getAirlinesById(
        @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(airlineService.getAirlineById(id));
    }

    @GetMapping
    public ResponseEntity<Page<AirlineResponse>> getAllAirlines(Pageable pageable)
    {
        return ResponseEntity.ok(airlineService.getAllAirlines(pageable));
    }

    @GetMapping("/dropdown")
    public ResponseEntity<List<AirlineDropDownItem>> getAirlinesForDropDown()
    {
        return ResponseEntity.ok(airlineService.getAirlineDropDown());
    }
    @PutMapping
    public ResponseEntity<AirlineResponse> updatedAirline(
            @Valid @RequestBody AirlineRequest airlineRequest,
            @RequestHeader("X-user-Id") Long UserId
    ) throws Exception {
        return ResponseEntity.ok(airlineService.updateAirline(airlineRequest,UserId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirline(
            @PathVariable Long id,
            @RequestHeader("X-user-Id") Long UserId
    ) throws Exception {
        airlineService.deleteAirlines(id,UserId);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Airline Delete Successfully");
        return ResponseEntity.ok(
                apiResponse
        );
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<AirlineResponse> approveAirline(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatuByAdmin(id, AirlineStatus.ACTIVE));
    }

    @PostMapping("/{id}/suspend")
    public ResponseEntity<AirlineResponse> suspendAirline(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatuByAdmin(id, AirlineStatus.INACTIVE));
    }

    @PostMapping("/{id}/ban")
    public ResponseEntity<AirlineResponse> BanAirline(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airlineService.changeStatuByAdmin(id, AirlineStatus.BANNED));
    }

}
