package com.FlyNova.Airline_Core_Service.Respository;

import com.FlyNova.Airline_Core_Service.Model.Airline;
import com.FlyNova.enums.AirlineStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AirlineRepository extends JpaRepository<Airline,Long>
{
    Optional<Airline> findByOwnerId(Long OwnerId);
    List<Airline> findByStatus(AirlineStatus status);
}
