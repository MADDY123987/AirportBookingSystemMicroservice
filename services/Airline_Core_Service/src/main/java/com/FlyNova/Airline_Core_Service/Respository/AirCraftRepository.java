package com.FlyNova.Airline_Core_Service.Respository;

import com.FlyNova.Airline_Core_Service.Model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirCraftRepository extends JpaRepository<Aircraft,Long> {
    List<Aircraft> findByAirlineId(Long airlineId);
    boolean existsByCode(String code);
    Aircraft findByIdAndAirlineId(Long id,Long AirlineId);
}
