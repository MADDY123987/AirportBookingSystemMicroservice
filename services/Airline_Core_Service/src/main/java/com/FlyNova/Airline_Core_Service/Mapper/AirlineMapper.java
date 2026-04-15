package com.FlyNova.Airline_Core_Service.Mapper;


import com.FlyNova.Airline_Core_Service.Model.Airline;
import com.FlyNova.embeddable.Support;
import com.FlyNova.payload.request.AirlineRequest;
import com.FlyNova.payload.response.AirlineResponse;

public class AirlineMapper {

    public static Airline toEntity(AirlineRequest request,Long OwnerId)
    {
        if (request==null) { return null; }
        Airline airline=Airline.builder()
                .iataCode(request.getIataCode())
                .icaoCode(request.getIcaoCode())
                .name(request.getName())
                .alias(request.getAlias())
                .logoUrl(request.getLogoUrl())
                .website(request.getWebsite())
                .status(request.getStatus())
                .alliance(request.getAlliance())
                .headquartersCityId(request.getHeadquartersCityId())
                .OwnerId(OwnerId)
                .build();

        if(request.getSupportEmail()!=null || request.getSupportPhone()!=null || request.getSupportHours()!=null)
        {
            airline.setSupport(
                    Support.builder()
                            .Email(request.getSupportEmail())
                            .Phone(request.getSupportPhone())
                            .Hours(request.getSupportHours())
                            .build()
            );
        }
        return airline;
    }
    public static AirlineResponse toResponse(Airline airline)
    {
        if(airline==null) return null;
        return AirlineResponse.builder()
                .id(airline.getId())
                .iataCode(airline.getIataCode())
                .icaoCode(airline.getIcaoCode())
                .name(airline.getName())
                .alias(airline.getAlias())
                .logoUrl(airline.getLogoUrl())
                .website(airline.getWebsite())
                .status(airline.getStatus())
                .alliance(airline.getAlliance())
                .support(airline.getSupport())
                .createdAt(airline.getCreatedAt())
                .updatedAt(airline.getUpdatedAt())
                .ownerId(airline.getOwnerId())
                .updatedById(airline.getUpdatedById())
                .build();
    }
    public static void updateEntity(Airline airline,AirlineRequest request)
    {
        if(airline==null) return;

        airline.setIataCode(request.getIataCode());
        airline.setIcaoCode(request.getIcaoCode());
        airline.setName(request.getName());
        airline.setAlias(request.getAlias());
        airline.setLogoUrl(request.getLogoUrl());
        airline.setWebsite(request.getWebsite());
        airline.setStatus(request.getStatus());
        airline.setAlliance(request.getAlliance());
        airline.setHeadquartersCityId(request.getHeadquartersCityId());

        if(airline.getSupport()==null)
        {
            airline.setSupport(new Support());
        }
        airline.getSupport().setEmail(request.getSupportEmail());
        airline.getSupport().setPhone(request.getSupportPhone());
        airline.getSupport().setHours(request.getSupportHours());

    }
}
