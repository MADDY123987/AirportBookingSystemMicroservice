package com.FlyNova.Pricing_Service.Service.Impl;

import com.FlyNova.Pricing_Service.Mapper.BaggagePolicyMapper;
import com.FlyNova.Pricing_Service.Model.BaggagePolicy;
import com.FlyNova.Pricing_Service.Model.Fare;
import com.FlyNova.Pricing_Service.Respository.BaggagePolicyRepository;
import com.FlyNova.Pricing_Service.Respository.FareRepository;
import com.FlyNova.Pricing_Service.Service.BaggagePolicyService;
import com.FlyNova.payload.request.BaggagePolicyRequest;
import com.FlyNova.payload.response.BaggagePolicyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BaggagePolicyServiceImpl implements BaggagePolicyService {
    private final BaggagePolicyRepository baggagePolicyRepository;
    private final FareRepository fareRepository;
    @Override
    public BaggagePolicyResponse createBaggagePolicy(BaggagePolicyRequest request) throws Exception {
        Fare fare=fareRepository.findById(request.getFareId())
                .orElseThrow(
                        ()->new Exception("Baggage Policy Not Found")
                );
        if(baggagePolicyRepository.existsByFareId(fare.getId()))
        {
            throw new Exception("Baggage Policy Already Exists");
        }
        BaggagePolicy baggagePolicy= BaggagePolicyMapper.toEntity(request,fare);
        BaggagePolicy saved=baggagePolicyRepository.save(baggagePolicy);
        return BaggagePolicyMapper.toResponse(saved);

    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyById(Long id) throws Exception {
        BaggagePolicy baggagePolicy=baggagePolicyRepository.findById(id).orElseThrow(
                ()->new Exception("policy not found with id")
        );
        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public BaggagePolicyResponse getBaggagePolicyByFareId(Long fareId) {
        BaggagePolicy baggagePolicy=baggagePolicyRepository.findByFareId(fareId);
        return BaggagePolicyMapper.toResponse(baggagePolicy);
    }

    @Override
    public List<BaggagePolicyResponse> getAllBaggagePoliciesByAirlineId(Long airlineId) {
        return baggagePolicyRepository.findByAirlineId(airlineId)
                .stream().map(
                        BaggagePolicyMapper::toResponse
                ).collect(Collectors.toList());
    }

    @Override
    public BaggagePolicyResponse updateBaggagePolicy(Long id, BaggagePolicyRequest request) throws Exception {
        BaggagePolicy baggagePolicy=baggagePolicyRepository.findById(id).orElseThrow(
                ()->new Exception("policy not found with id")
        );
        BaggagePolicyMapper.updateEntity(request,baggagePolicy);
        BaggagePolicy saved=baggagePolicyRepository.save(baggagePolicy);
        return BaggagePolicyMapper.toResponse(saved);
    }

    @Override
    public void deleteBaggagePolicy(Long id) throws Exception {
        BaggagePolicy baggagePolicy=baggagePolicyRepository.findById(id).orElseThrow(
                ()->new Exception("Baggage policy not found with id:"+id)
        );
        baggagePolicyRepository.delete(baggagePolicy);
    }
}
