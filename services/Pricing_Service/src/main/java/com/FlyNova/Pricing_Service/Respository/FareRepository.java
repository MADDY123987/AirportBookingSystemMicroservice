package com.FlyNova.Pricing_Service.Respository;

import com.FlyNova.Pricing_Service.Model.Fare;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FareRepository extends JpaRepository<Fare,Long> {
    List<Fare> findByFlightId(Long flightId);
    Page<Fare> findByFlightId(Long flightId, Pageable pageable);
    List<Fare> findByCabinClassId(Long flightId);
    List<Fare> findByFlightIdAndCabinClassId(Long flightId,Long cabinClassId);
    List<Fare> findByFlightIdInAndCabinClassId(List<Long>flightId,Long cabinClassId);

    boolean existsByFlightIdAndCabinClassIdAndName(
            Long flightId,Long cabinClassId,String name
    );
    boolean existsByFlightIdAndCabinClassIdAndNameAndIdNot(
            Long flightId,
            Long CabinClassId,
            String name,
            Long id
    );


}
