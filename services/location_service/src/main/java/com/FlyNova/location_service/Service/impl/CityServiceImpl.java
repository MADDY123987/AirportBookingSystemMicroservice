package com.FlyNova.location_service.Service.impl;
import com.FlyNova.location_service.Mapper.CityMapper;
import com.FlyNova.location_service.Model.City;
import com.FlyNova.location_service.Repository.CityRepository;
import com.FlyNova.location_service.Service.CityService;
import com.FlyNova.payload.request.CityRequest;
import com.FlyNova.payload.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
@Service
@RequiredArgsConstructor

public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    @Override
    public CityResponse createCity(CityRequest request)  {
        if (cityRepository.existsByCityCode(request.getCityCode())) {
            throw new RuntimeException("City with given code already exists");
        }

        City city = CityMapper.toEntity(request);
        City result = cityRepository.save(city);

        return CityMapper.toResponse(result);
    }

    @Override
    public CityResponse getCityById(Long id){
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not exist with given id"));

        return CityMapper.toResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest request) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not exist with id"));

        if (cityRepository.existsByCityCodeAndIdNot(request.getCityCode(), id)) {
            throw new RuntimeException("City with given code already exists");
        }
        City updatedCity=cityRepository.save(CityMapper.updateEntity(city,request));
        return CityMapper.toResponse(updatedCity);
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("City not exist with given id"));

        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword,pageable).map(CityMapper::toResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode,pageable)
                .map(CityMapper::toResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
