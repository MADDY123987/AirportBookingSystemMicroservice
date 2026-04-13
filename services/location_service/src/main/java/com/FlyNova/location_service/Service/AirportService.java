package com.FlyNova.location_service.Service;

import com.FlyNova.payload.request.AirportRequest;
import com.FlyNova.payload.response.AirportResponse;

import java.util.List;

public interface AirportService {
    AirportResponse createAirport(AirportRequest request) throws Exception;
    AirportResponse getAiportById(Long id);
    List<AirportResponse> getAllAirports();

    AirportResponse updateAirport(Long id,AirportRequest request);

    void deleteAirport(Long id);
    List<AirportResponse> getAirportByCityId(Long cityId);

}
