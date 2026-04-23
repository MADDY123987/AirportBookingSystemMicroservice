package com.FlyNova.Pricing_Service.Service.Impl;

import com.FlyNova.Pricing_Service.Mapper.FareMapper;
import com.FlyNova.Pricing_Service.Model.Fare;
import com.FlyNova.Pricing_Service.Respository.FareRepository;
import com.FlyNova.Pricing_Service.Service.FareService;
import com.FlyNova.payload.request.FareRequest;
import com.FlyNova.payload.response.FareResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;
    @Override
    public FareResponse createFare(FareRequest request) throws Exception {
        if(fareRepository.existsByFlightIdAndCabinClassIdandName(
                request.getFlightId(), request.getCabinClassId(), request.getName()
        ))
        {
            throw new Exception("Fare Already Exists with the Provided name");
        }
        Fare fare= FareMapper.toEntity(request);
        Fare Saved=fareRepository.save(fare);
        return FareMapper.toResponse(Saved);
    }

    @Override
    public FareResponse getFareById(Long id) throws Exception {
        Fare fare=fareRepository.findById(id).orElseThrow(
                ()->new Exception("Fare Not Found with given Id")
        );
        return FareMapper.toResponse(fare);
    }

    @Override
    public List<FareResponse> getFareByFlightIdAndCabinClassId(Long flightId, Long cabinClassId) {
        return fareRepository.findByFlightIdandCabinClassId(
                flightId, cabinClassId
        ).stream().map(
                FareMapper::toResponse
        ).toList();
    }

    @Override
    public FareResponse updateFare(Long id, FareRequest request) throws Exception {
        Fare fare=fareRepository.findById(id).orElseThrow(
                ()->new Exception("Fare Not Found with given Id")
        );
        if(fareRepository.existsByFlightIdAndCabinClassIdAndNameAndIdNot(
                request.getFlightId(),
                request.getCabinClassId(),
                request.getName(),
                fare.getId()
        ));
        FareMapper.updateEntity(request,fare);
        Fare updated=fareRepository.save(fare);
        return FareMapper.toResponse(updated);
    }

    @Override
    public void deleteFare(Long id) throws Exception {
        Fare fare=fareRepository.findById(id).orElseThrow(
                ()->new Exception("Fare Not Found With Given Id")
        );
        fareRepository.delete(fare);
    }

    @Override
    public List<Fare> getFares() {
        return fareRepository.findAll();
    }

    @Override
    public Map<Long, FareResponse> getLowestFarePerFlight(List<Long> flightIds, Long cabinClassId) {
        if(flightIds==null||flightIds.isEmpty()) return Map.of();

        List<Fare> fares=fareRepository.findByFlightIdInandCabinClassId(
                flightIds,cabinClassId
        );
        Map<Long,FareResponse> result=fares.stream()
                .collect(Collectors.toMap(
                        Fare::getFlightId,
                        fare -> fare,
                        (existing,candidate)->
                                candidate.getTotalPrice()<existing.getTotalPrice()
                        ?candidate:existing
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e-> FareMapper.toResponse(e.getValue())
                ));
            return  result;
    }

    @Override
    public Map<Long, FareResponse> getFaresByIds(List<Long> ids) {
        List<Fare> fares=fareRepository.findAllById(ids);
        return fares.stream()
                .collect(Collectors.toMap(
                        Fare::getId,
                        FareMapper::toResponse
                ));
    }
}
