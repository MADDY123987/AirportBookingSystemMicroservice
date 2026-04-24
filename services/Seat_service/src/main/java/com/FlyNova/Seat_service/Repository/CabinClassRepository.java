package com.FlyNova.Seat_service.Repository;

import com.FlyNova.Seat_service.Model.CabinClass;
import com.FlyNova.enums.CabinClassType;
import org.aspectj.weaver.ast.Literal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CabinClassRepository extends JpaRepository<CabinClass,Long> {

   List<CabinClass> findByAircraftId(Long aircraftId);
   CabinClass findByAircraftIdAndName(Long aircraftId, CabinClassType name);

   Boolean existsByCodeAndAircraftId(String code,Long aircraftId);
   Boolean existsByCodeAndAircraftIAndIdNot(String code,Long aircraftId,Long id);

}
