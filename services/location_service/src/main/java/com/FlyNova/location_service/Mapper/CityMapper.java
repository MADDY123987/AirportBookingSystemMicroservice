package com.FlyNova.location_service.Mapper;

import com.FlyNova.location_service.Model.City;
import com.FlyNova.payload.request.CityRequest;
import com.FlyNova.payload.response.CityResponse;

public class CityMapper {
    public static City toEntity(CityRequest request)
    {
        if(request==null) return null;

        return City.builder()
                .name(request.getName())
                .cityCode(request.getCityCode())
                .countryCode(request.getCountryCode())
                .countryName(request.getCountryName())
                .regionCode(request.getRegionCode())
                .timeZoneId(request.getTimeZoneOffSet())
                .build();
    }
   public static CityResponse toResponse(City city)
   {
       if(city==null)return null;
       return CityResponse.builder()
               .id(city.getId())
               .name(city.getName())
               .cityCode(city.getCityCode())
               .CountryCode(city.getCountryCode())
               .CountryName(city.getCountryName())
               .regionCode(city.getRegionCode())
               .timeZoneOffset(city.getTimeZoneId())
               .build();
   }
   public static City updateEntity(City city,CityRequest request)
   {
       if(request.getName()!=null)
       {
           city.setName(request.getName().trim());
       }
       if(request.getCityCode()!=null)
       {
           city.setCityCode(request.getCityCode().trim());
       }
       if(request.getCountryCode()!=null)
       {
           city.setCountryCode(request.getCountryCode().toUpperCase().trim());
       }
       if(request.getCountryName()!=null)
       {
           city.setCountryName(request.getCountryName().toUpperCase().trim());
       }
       if(request.getRegionCode()!=null)
       {
           city.setRegionCode(request.getRegionCode().trim());
       }
       if (request.getTimeZoneOffSet()!=null)
       {
           city.setTimeZoneId(request.getTimeZoneOffSet().trim());
       }
       return city;
   }
}
