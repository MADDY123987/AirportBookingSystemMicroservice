package com.FlyNova.Pricing_Service.Service.Impl;

import com.FlyNova.Pricing_Service.Mapper.FareRulesMapper;
import com.FlyNova.Pricing_Service.Model.Fare;
import com.FlyNova.Pricing_Service.Model.FareRules;
import com.FlyNova.Pricing_Service.Respository.FareRepository;
import com.FlyNova.Pricing_Service.Respository.FareRulesRepository;
import com.FlyNova.Pricing_Service.Service.FareRulesService;
import com.FlyNova.payload.request.FareRulesRequest;
import com.FlyNova.payload.response.FareRulesResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FareRulesServiceImpl implements FareRulesService {
    private final FareRulesRepository fareRulesRepository;
    private final FareRepository fareRepository;
    @Override
    public FareRulesResponse CreateFareRule(FareRulesRequest request) {
        Fare fare=fareRepository.findById(request.getFareId())
                .orElseThrow(()->new EntityNotFoundException(
                        "Fare Not Foudn With id"+ request.getFareId()));
        if(fareRulesRepository.existsByFareId(request.getFareId()))
        {
            throw new IllegalArgumentException("Fare Rules Already Exists with Fare id"+request.getFareId());
        }
        FareRules  savedRules= FareRulesMapper.toEntity(request,fare);
        FareRules saved=fareRulesRepository.save(savedRules);
        return FareRulesMapper.toResponse(saved);
    }

    @Override
    public FareRulesResponse getFareRulesById(Long id) throws Exception {
        FareRules fareRules=fareRulesRepository.findById(id).orElseThrow(
                ()->new Exception("fare rules Not Found"+id)
        );
        return FareRulesMapper.toResponse(fareRules);

    }

    @Override
    public FareRulesResponse getFareRulesByFareId(Long fareId) throws Exception {
        FareRules fareRules=fareRulesRepository.findByFareId(fareId).orElseThrow(
                ()->new Exception("Fare Not Found with fareId"+fareId)
        );
        return FareRulesMapper.toResponse(fareRules);
    }

    @Override
    public List<FareRulesResponse> getFareRulesByAirlineId(Long airlineId) {
        return fareRulesRepository.findByAirlineId(airlineId).stream()
                .map(FareRulesMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FareRulesResponse updateFareRule(Long id, FareRulesRequest request) throws Exception {
        FareRules existing=fareRulesRepository.findById(id).orElseThrow(
                ()->new Exception("fare rules not found with id"+id)
        );
        FareRulesMapper.updateEntity(request,existing);
        FareRules Saved=fareRulesRepository.save(existing);
        return FareRulesMapper.toResponse(Saved);
    }

    @Override
    public void deleteFareRules(Long id) throws Exception {
        FareRules fareRules=fareRulesRepository.findById(id).orElseThrow(
                ()->new Exception("Fare rule Not Found with id"+id)
        );
        fareRulesRepository.delete(fareRules);
    }
}
