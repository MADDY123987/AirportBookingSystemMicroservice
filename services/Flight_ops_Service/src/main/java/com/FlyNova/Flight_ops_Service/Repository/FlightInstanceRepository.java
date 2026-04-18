package com.FlyNova.Flight_ops_Service.Repository;

import com.FlyNova.Flight_ops_Service.Model.FlightInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface FlightInstanceRepository extends JpaRepository<FlightInstance,Long> {

    @Query("SELECT fi FROM FlightInstance fi WHERE fi.AirlineId = :airlineId" +
            " AND (:departureAirportId IS NULL OR fi.DepartureAirportId = :departureAirportId)" +
            " AND (:arrivalAirportId IS NULL OR fi.ArrivalAirportId = :arrivalAirportId)" +
            " AND (:flightId IS NULL OR fi.flight.id = :flightId)" +
            " AND (:dayStart IS NULL OR fi.departureDateTime >= :dayStart)" +
            " AND (:dayEnd IS NULL OR fi.departureDateTime < :dayEnd)")
    Page<FlightInstance> findByAirlineIdWithFilters(
            @Param("airlineId") Long airlineId,
            @Param("departureAirportId") Long departureAirportId,
            @Param("arrivalAirportId") Long arrivalAirportId,
            @Param("flightId") Long flightId,
            @Param("dayStart") LocalDateTime dayStart,
            @Param("dayEnd") LocalDateTime dayEnd,
            Pageable pageable);
}
