package com.FlyNova.Seat_service.Repository;

import com.FlyNova.Seat_service.Model.FlightInstanceCabin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightInstanceCabinRepository extends JpaRepository<FlightInstanceCabin,Long> {
    Page<FlightInstanceCabin>findBYFlightInstanceId(Long flightInstanceId, Pageable pageable);
    FlightInstanceCabin findFlightInstanceIdAndCabinClassId(Long flightInstanceId,Long cabinClassId);
}
