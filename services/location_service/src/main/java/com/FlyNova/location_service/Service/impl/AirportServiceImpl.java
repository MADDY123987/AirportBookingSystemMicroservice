package com.FlyNova.location_service.Service.impl;

import com.FlyNova.location_service.Mapper.AirportMapper;
import com.FlyNova.location_service.Model.Airport;
import com.FlyNova.location_service.Model.City;
import com.FlyNova.location_service.Repository.AirportRepository;
import com.FlyNova.location_service.Repository.CityRepository;
import com.FlyNova.location_service.Service.AirportService;
import com.FlyNova.payload.request.AirportRequest;
import com.FlyNova.payload.response.AirportResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest request) {

        if (airportRepository.findByIataCode(request.getIataCode()).isPresent()) {
            throw new RuntimeException("Airport with IATA code already exists");
        }

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));

        Airport airport = AirportMapper.toEntity(request);
        airport.setCity(city);

        Airport savedAirport = airportRepository.save(airport);

        return AirportMapper.toResponse(savedAirport);
    }

    @Override
    public AirportResponse getAiportById(Long id) {

        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found with id " + id));

        return AirportMapper.toResponse(airport);
    }

    @Override
    public List<AirportResponse> getAllAirports() {

        return airportRepository.findAll()
                .stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest request) {

        Airport existingAirport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found with id " + id));

        if (request.getIataCode() != null &&
                !existingAirport.getIataCode().equals(request.getIataCode()) &&
                airportRepository.findByIataCode(request.getIataCode()).isPresent()) {

            throw new RuntimeException("Airport with IATA code already exists");
        }

        AirportMapper.updateEntity(request, existingAirport);
        Airport updatedAirport = airportRepository.save(existingAirport);

        return AirportMapper.toResponse(updatedAirport);
    }

    @Override
    public void deleteAirport(Long id) {

        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found with id " + id));

        airportRepository.delete(airport);
    }

    @Override
    public List<AirportResponse> getAirportByCityId(Long cityId) {

        return airportRepository.findByCityId(cityId)
                .stream()
                .map(AirportMapper::toResponse)
                .collect(Collectors.toList());
    }
}