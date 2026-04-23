package com.FlyNova.Pricing_Service.Service;

import com.FlyNova.payload.request.FareRulesRequest;
import com.FlyNova.payload.response.FareRulesResponse;

import java.util.List;

public interface FareRulesService {
    FareRulesResponse CreateFareRule(FareRulesRequest request) throws Exception;
    FareRulesResponse getFareRulesById(Long id) throws Exception;
    FareRulesResponse getFareRulesByFareId(Long fareId) throws Exception;
    List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId);
    FareRulesResponse updateFareRule(Long id,FareRulesRequest request) throws Exception;
    void deleteFareRules(Long id) throws Exception;
}
