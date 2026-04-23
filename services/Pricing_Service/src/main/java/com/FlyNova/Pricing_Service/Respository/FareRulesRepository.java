package com.FlyNova.Pricing_Service.Respository;

import com.FlyNova.Pricing_Service.Model.FareRules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FareRulesRepository extends JpaRepository<FareRules,Long> {
    Optional<FareRules> findByFareId(Long fareId);
    List<FareRules> findByAirlineId(Long AirlineId);
    Boolean existsByFareId(Long fareId);
}
