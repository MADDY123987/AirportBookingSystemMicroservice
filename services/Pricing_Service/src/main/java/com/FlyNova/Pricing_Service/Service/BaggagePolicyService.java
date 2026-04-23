package com.FlyNova.Pricing_Service.Service;

import com.FlyNova.payload.request.BaggagePolicyRequest;
import com.FlyNova.payload.response.BaggagePolicyResponse;

import java.util.List;

public interface BaggagePolicyService {
    BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest request) throws Exception;
    BaggagePolicyResponse getBaggagePolicyById(Long id) throws Exception;
    BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId);
    List<BaggagePolicyResponse> getAllBaggagePoliciesByAirlineId(Long airlineId);
    BaggagePolicyResponse updateBaggagePolicy(Long id,BaggagePolicyRequest request) throws Exception;
    void deleteBaggagePolicy(Long id) throws Exception;
}
