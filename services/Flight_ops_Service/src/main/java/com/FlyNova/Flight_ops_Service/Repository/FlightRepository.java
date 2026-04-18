package com.FlyNova.Flight_ops_Service.Repository;

import com.FlyNova.Flight_ops_Service.Model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("""
    SELECT f FROM Flight f
    WHERE f.AirlineId = :airlineId
    AND (:depId IS NULL OR f.DepartureAirportId = :depId)
    AND (:arrId IS NULL OR f.ArrivalAirportId = :arrId)
""")
    Page<Flight> findByAirlineId(
            @Param("airlineId") Long airlineId,
            @Param("depId") Long depId,
            @Param("arrId") Long arrId,
            Pageable pageable
    );
    Boolean existsByFlightNumber(String flightNumber);
    Boolean existsByFlightNumberAndIdNot(String flightNumber,Long id);//
    Optional <Flight> findByAirlineIdAndId(Long airlineId, Long id);
}
