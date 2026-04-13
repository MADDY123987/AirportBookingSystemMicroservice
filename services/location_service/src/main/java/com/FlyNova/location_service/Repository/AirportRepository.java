package com.FlyNova.location_service.Repository;

import com.FlyNova.location_service.Model.Airport;
import com.FlyNova.payload.response.AirportResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirportRepository  extends JpaRepository<Airport,Long> {

    Optional<Airport> findByIataCode(String iataCode);
    List<Airport> findByCityId(Long cityId);


}
