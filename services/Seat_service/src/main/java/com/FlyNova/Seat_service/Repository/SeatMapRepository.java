package com.FlyNova.Seat_service.Repository;

import com.FlyNova.Seat_service.Model.SeatMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatMapRepository extends JpaRepository<SeatMap,Long> {
    SeatMap findByCabinClassId(Long CabinClassId);
    boolean existsByAirlineIdAndCabinClassIdAndName(Long airlineId,Long cabinClassId,String name);

}
