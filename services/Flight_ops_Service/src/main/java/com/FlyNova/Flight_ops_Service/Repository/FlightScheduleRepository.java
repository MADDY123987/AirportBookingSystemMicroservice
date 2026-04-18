package com.FlyNova.Flight_ops_Service.Repository;

import com.FlyNova.Flight_ops_Service.Model.FlightSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightScheduleRepository extends JpaRepository<FlightSchedule,Long> {

    List<FlightSchedule> findFlightAirlineById(Long airlineId);

}
