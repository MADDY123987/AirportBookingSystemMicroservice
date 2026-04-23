package com.FlyNova.Pricing_Service.Controller;


import com.FlyNova.Pricing_Service.Service.BaggagePolicyService;
import com.FlyNova.payload.request.BaggagePolicyRequest;
import com.FlyNova.payload.response.BaggagePolicyResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/baggage-policies")
public class BaggagePolicyController {
    private final BaggagePolicyService baggagePolicyService;

    @PostMapping
    public ResponseEntity<BaggagePolicyResponse> createBaggagePolicy(
            @RequestBody BaggagePolicyRequest request
            ) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(baggagePolicyService.createBaggagePolicy(request));
    }

    @GetMapping("{/id}")
    public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(baggagePolicyService.getBaggagePolicyById(id));
    }

    @GetMapping("/fare/{fareId}")
    public ResponseEntity<BaggagePolicyResponse> getBaggagePolicyByFareId(
            @PathVariable Long fareId
    )
    {
        return ResponseEntity.ok(baggagePolicyService.getBaggagePolicyByFareId(fareId));
    }
    @GetMapping("/airline/{airlineId}")
    public ResponseEntity<List<BaggagePolicyResponse>> getAllBaggagePoliciesByAirlineId(
            @PathVariable Long airlineId
    )
    {
        return ResponseEntity.ok(baggagePolicyService.getAllBaggagePoliciesByAirlineId(airlineId));
    }
    @PutMapping("{/id}")
    public ResponseEntity<BaggagePolicyResponse> updateBaggagePolicy(
            @PathVariable Long id,
            @Valid @RequestBody BaggagePolicyRequest request
    ) throws Exception {
        return ResponseEntity.ok(baggagePolicyService.updateBaggagePolicy(id,request));
    }
    @DeleteMapping("{/id}")
    public ResponseEntity<Void>deleteBaggagePolicy(
            @PathVariable  Long id
    ) throws Exception {
        baggagePolicyService.deleteBaggagePolicy(id);
        return ResponseEntity.noContent().build();
    }


}
