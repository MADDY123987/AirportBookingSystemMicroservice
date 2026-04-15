package com.FlyNova.Airline_Core_Service.Service;

import com.FlyNova.Airline_Core_Service.Model.Airline;
import com.FlyNova.enums.AirlineStatus;
import com.FlyNova.payload.request.AirlineRequest;
import com.FlyNova.payload.response.AirlineDropDownItem;
import com.FlyNova.payload.response.AirlineResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AirlineService {
    AirlineResponse createAirline(AirlineRequest request,Long OwnerId);
    AirlineResponse getAirlineByOwner(Long OwnerId) throws Exception;
    AirlineResponse getAirlineById(Long id) throws Exception;
    Page<AirlineResponse> getAllAirlines(Pageable pageable);
    AirlineResponse updateAirline(AirlineRequest request,Long OwnerId) throws Exception;
    void deleteAirlines(Long id,Long OwnerId) throws Exception;
    AirlineResponse changeStatuByAdmin(Long AirlineId, AirlineStatus status) throws Exception;
    List<AirlineDropDownItem>getAirlineDropDown();
}
