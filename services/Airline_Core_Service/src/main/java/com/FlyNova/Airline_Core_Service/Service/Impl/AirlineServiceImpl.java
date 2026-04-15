package com.FlyNova.Airline_Core_Service.Service.Impl;

import com.FlyNova.Airline_Core_Service.Mapper.AirlineMapper;
import com.FlyNova.Airline_Core_Service.Model.Airline;
import com.FlyNova.Airline_Core_Service.Respository.AirlineRepository;
import com.FlyNova.Airline_Core_Service.Service.AirlineService;
import com.FlyNova.enums.AirlineStatus;
import com.FlyNova.payload.request.AirlineRequest;
import com.FlyNova.payload.response.AirlineDropDownItem;
import com.FlyNova.payload.response.AirlineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {
    private final AirlineRepository airlineRepository;

    @Override
    public AirlineResponse createAirline(AirlineRequest request, Long OwnerId) {
        Airline airline= AirlineMapper.toEntity(request,OwnerId);
        Airline savedAirline=airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public AirlineResponse getAirlineByOwner(Long OwnerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(OwnerId)
                .orElseThrow(
                        ()->new Exception("Airline Not Found with Owner Id"+OwnerId)
                );
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public AirlineResponse getAirlineById(Long id) throws Exception {
        Airline airline=airlineRepository.findById(id)
                .orElseThrow(
                        ()->new Exception("Airline Not Found with Id"+ id)
                );
        return AirlineMapper.toResponse(airline);
    }

    @Override
    public Page<AirlineResponse> getAllAirlines(Pageable pageable) {
        Page<Airline> airlinePage=airlineRepository.findAll(pageable);
        return airlinePage.map(AirlineMapper::toResponse);
    }

    @Override
    public AirlineResponse updateAirline(AirlineRequest request, Long OwnerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(OwnerId)
                .orElseThrow(
                        ()->new Exception("Airline Not Found With Id")
                );
        AirlineMapper.updateEntity(airline,request);
        Airline savedAirline=airlineRepository.save(airline);
        return AirlineMapper.toResponse(savedAirline);
    }

    @Override
    public void deleteAirlines(Long id, Long OwnerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(OwnerId)
                .orElseThrow(
                        ()->new Exception("Airline Not Found With Id"+id)
                );
        airlineRepository.deleteById(id);
    }

    @Override
    public AirlineResponse changeStatuByAdmin(Long AirlineId, AirlineStatus status) throws Exception {
        Airline airline=airlineRepository.findById(AirlineId)
                .orElseThrow(
                        ()->new Exception("Airline Not Found With Id"+AirlineId)
                );
        airline.setStatus(status);
        Airline updatedAirline=airlineRepository.save(airline);
        return AirlineMapper.toResponse(updatedAirline);
    }

    @Override
    public List<AirlineDropDownItem> getAirlineDropDown() {
        return airlineRepository.findByStatus(AirlineStatus.ACTIVE)
                .stream()
                .map(a->AirlineDropDownItem.builder()
                        .id(a.getId())
                        .name(a.getName())
                        .icaCode(a.getIcaoCode())
                        .iataCode(a.getIataCode())
                        .logoUrl(a.getLogoUrl())
                        .build()).toList();
    }
}
