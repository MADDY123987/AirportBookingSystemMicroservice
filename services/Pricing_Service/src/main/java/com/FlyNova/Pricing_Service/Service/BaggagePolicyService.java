package com.FlyNova.Pricing_Service.Service;

import com.FlyNova.Pricing_Service.Model.BaggagePolicy;
import com.FlyNova.payload.request.BaggagePolicyRequest;
import com.FlyNova.payload.response.BaggagePolicyResponse;

public interface BaggagePolicyService {
    BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest request);

}
