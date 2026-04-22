package com.FlyNova.Pricing_Service.Service.Impl;

import com.FlyNova.Pricing_Service.Model.Fare;
import com.FlyNova.Pricing_Service.Service.FareService;
import com.FlyNova.payload.request.FareRequest;
import com.FlyNova.payload.response.FareResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FareServiceImpl implements FareService {
    @Override
    public FareResponse createFare(FareRequest request) {
        return null;
    }

    @Override
    public FareResponse getFareById(Long id) {
        return null;
    }

    @Override
    public List<FareResponse> getFareByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {
        return List.of();
    }

    @Override
    public FareResponse updateFare(Long id, FareRequest request) {
        return null;
    }

    @Override
    public void deleteFare(Long id) {

    }

    @Override
    public List<Fare> getFares() {
        return List.of();
    }

    @Override
    public Map<Long, FareResponse> getLowestFarePerFlight(List<Long> flightIds, Long cabinClassId) {
        return Map.of();
    }

    @Override
    public Map<Long, FareResponse> getFaresByIds(List<Long> ids) {
        return Map.of();
    }
}
