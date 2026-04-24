package com.FlyNova.Seat_service.Service;

import com.FlyNova.enums.CabinClassType;
import com.FlyNova.payload.request.CabinClassRequest;
import com.FlyNova.payload.response.CabinClassResponse;

import java.util.List;

public interface CabinClassService {
    CabinClassResponse createCabinClass(CabinClassRequest request) throws Exception;
    CabinClassResponse getCabinClassById(Long id) throws Exception;
    List<CabinClassResponse> getCabinClassesByAircraftId(Long aircraftId);
    CabinClassResponse getAirCraftIdAndName(Long aircraftId, CabinClassType name);
    CabinClassResponse updateCabinClass(Long id,CabinClassRequest request) throws Exception;
    void deleteCabinClass(Long id) throws Exception;
}
