package com.FlyNova.Airline_Core_Service.Mapper;

import com.FlyNova.Airline_Core_Service.Model.Aircraft;
import com.FlyNova.Airline_Core_Service.Model.Airline;
import com.FlyNova.payload.request.AircraftRequest;
import com.FlyNova.payload.response.AircraftResponse;

public class AirCraftMapper {

    public static Aircraft toEntity(AircraftRequest request,Airline airline)
    {
        if(request==null)
        {
            return null;
        }
        return Aircraft.builder()
                .code(request.getCode())
                .model(request.getModel())
                .manufacturer(request.getManufacturer())
                .seatingCapacity(request.getSeatingCapacity())
                .economySeats(request.getEconomySeats())
                .PremiumEconomySeats(request.getPremuimSeats())
                .buinessSeats(request.getBuinessSeats())
                .firstClassSeats(request.getFirstClassSeats())
                .rangeKm(request.getRangeKm())
                .cruisingSpeedKmh(request.getCruisingSpeedKmh())
                .maxAltitudeFt(request.getMaxAltitudeFt())
                .YearOfManufacture(request.getYearOfManufacture())
                .registeredDate(request.getRegistrationDate())
                .nextMaintenanceDate(request.getNextMaintenanceDate())
                .status(request.getStatus())
                .isAvailable(request.getIsAvailable())
                .airline(airline)
                .currentAirportId(request.getCurrentAirportId())
                .build();
    }
    public static AircraftResponse toResponse(Aircraft aircraft)
    {
        if(aircraft==null) return null;
        return AircraftResponse.builder()
                .id(aircraft.getId())
                .code(aircraft.getCode())
                .model(aircraft.getModel())
                .manufacturer(aircraft.getManufacturer())
                .seatingCapacity(aircraft.getSeatingCapacity())
                .economySeats(aircraft.getEconomySeats())
                .PremiumEconomySeats(aircraft.getPremiumEconomySeats())
                .buinessSeats(aircraft.getBuinessSeats())
                .firstClassSeats(aircraft.getFirstClassSeats())
                .rangeKm(aircraft.getRangeKm())
                .cruisingSpeedKmh(aircraft.getCruisingSpeedKmh())
                .maxAltitudeFt(aircraft.getMaxAltitudeFt())
                .YearOfManufacture(aircraft.getYearOfManufacture())
                .registeredDate(aircraft.getRegisteredDate())
                .nextMaintenanceDate(aircraft.getNextMaintenanceDate())
                .status(aircraft.getStatus())
                .isAvailable(aircraft.isAvailable())
                //Airline info(same Service Available Directyl)
                .airlineId(aircraft.getAirline()!=null ? aircraft.getAirline().getId():null )
                .airlineName(aircraft.getAirline()!=null ?aircraft.getAirline().getName():null)
                .airlineIataCode(aircraft.getAirline()!=null ?aircraft.getAirline().getIataCode():null)
                //AirPort is Cross-Service -Only ID Available here
                .currentAirportId(aircraft.getCurrentAirportId())
                //Computed Fields
                .totalSeats(aircraft.getTotalSeats())
                .requiredMaintenance(aircraft.requiredMaintenance())
                .isOperational(aircraft.isOperational())
                //Audit
                .createdAt(aircraft.getCreatedAt())
                .updatedAt(aircraft.getUpdatedAt())
                .build();
    }
    public static void updateEntity(Aircraft aircraft,AircraftRequest request)
    {
        if (aircraft==null || request==null) return;

        aircraft.setCode(request.getCode());
        aircraft.setModel(request.getModel());
        aircraft.setManufacturer(request.getManufacturer());
        aircraft.setSeatingCapacity(request.getSeatingCapacity());
        aircraft.setEconomySeats(request.getEconomySeats());
        aircraft.setBuinessSeats(request.getBuinessSeats());
        aircraft.setFirstClassSeats(request.getFirstClassSeats());
        aircraft.setPremiumEconomySeats(request.getPremuimSeats());
        aircraft.setRangeKm(request.getRangeKm());
        aircraft.setCruisingSpeedKmh(request.getCruisingSpeedKmh());
        aircraft.setMaxAltitudeFt(request.getMaxAltitudeFt());
        aircraft.setYearOfManufacture(request.getYearOfManufacture());
        aircraft.setRegisteredDate(request.getRegistrationDate());
        aircraft.setNextMaintenanceDate(request.getNextMaintenanceDate());
        aircraft.setStatus(request.getStatus());
        aircraft.setAvailable(request.getIsAvailable());
        aircraft.setCurrentAirportId(request.getCurrentAirportId());
    }
}
