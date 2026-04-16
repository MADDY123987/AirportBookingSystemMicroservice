package com.FlyNova.Airline_Core_Service.Service;

import com.FlyNova.Airline_Core_Service.Model.Aircraft;
import com.FlyNova.payload.request.AircraftRequest;
import com.FlyNova.payload.response.AircraftResponse;

import java.util.List;

public interface AirCraftService {

    AircraftResponse createAirCraft(AircraftRequest request,Long ownerId) throws Exception;
    AircraftResponse getAirCraftById(Long id) throws Exception;
    List<AircraftResponse> listAllAirCraftByOwner(Long ownerId) throws Exception;
    AircraftResponse updateAirCraft(Long id,AircraftRequest request,Long ownerId) throws Exception;
    void deleteAirCraft(Long id,Long ownerId) throws Exception;
}
