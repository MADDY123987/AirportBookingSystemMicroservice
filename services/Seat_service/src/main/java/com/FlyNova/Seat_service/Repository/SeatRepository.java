package com.FlyNova.Seat_service.Repository;

import com.FlyNova.Seat_service.Model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat,Long> {
    Boolean existsBySeatMapId(Long seatMapId);

}
