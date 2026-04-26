package com.FlyNova.Seat_service.Service;

import com.FlyNova.Seat_service.Model.FlightInstanceCabin;
import com.FlyNova.payload.request.FlightInstanceCabinRequest;
import com.FlyNova.payload.response.FlightInstanceCabinResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FlightInstanceCabinService {
    FlightInstanceCabinResponse createFlightInstanceCabin(FlightInstanceCabinRequest request) throws Exception;
    FlightInstanceCabinResponse getFlightInstanceCabinById(Long id) throws Exception;
    Page<FlightInstanceCabinResponse> getByFlightInstanceId(Long id, Pageable pageable);
    FlightInstanceCabinResponse  getByFlightInstanceIdAndCabinClassId(Long flightInstanceId,Long cabinClassId);
    FlightInstanceCabinResponse updateFlightInstanceCabin(Long id,FlightInstanceCabinRequest request) throws Exception;
    void deleteFlightInstanceCabin(Long id);
}
