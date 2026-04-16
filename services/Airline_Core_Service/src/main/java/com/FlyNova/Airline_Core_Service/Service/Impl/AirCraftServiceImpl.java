package com.FlyNova.Airline_Core_Service.Service.Impl;

import com.FlyNova.Airline_Core_Service.Mapper.AirCraftMapper;
import com.FlyNova.Airline_Core_Service.Model.Aircraft;
import com.FlyNova.Airline_Core_Service.Model.Airline;
import com.FlyNova.Airline_Core_Service.Respository.AirCraftRepository;
import com.FlyNova.Airline_Core_Service.Respository.AirlineRepository;
import com.FlyNova.Airline_Core_Service.Service.AirCraftService;
import com.FlyNova.payload.request.AircraftRequest;
import com.FlyNova.payload.response.AircraftResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class AirCraftServiceImpl implements AirCraftService
{
    private final AirlineRepository airlineRepository;
    private final AirCraftRepository airCraftRepository;

    @Override
    public AircraftResponse createAirCraft(AircraftRequest request, Long ownerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(()->new Exception("Airline does not Exits for this OwnerId")
                );
        Aircraft aircraft= AirCraftMapper.toEntity(request,airline);
        if(airCraftRepository.existsByCode(aircraft.getCode()))
        {
            throw new Exception("Code Already Exists With Another AirCraft");
        }
        if(aircraft.getSeatingCapacity()<aircraft.getTotalSeats())
        {
            throw new Exception("Seating Capacity cant be Exceed Total Seats");
        }
        return AirCraftMapper.toResponse(
                airCraftRepository.save(aircraft)
        );
    }

    @Override
    public AircraftResponse getAirCraftById(Long id) throws Exception {
        Aircraft aircraft= airCraftRepository.findById(id)
                .orElseThrow(
                        ()->new Exception("Aircraft Not Exist With Id")
                );
        return  AirCraftMapper.toResponse(aircraft);
    }

    @Override
    public List<AircraftResponse> listAllAirCraftByOwner(Long ownerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()->new Exception("This Owner dont have an Airline")
                );

        return airCraftRepository.findByAirlineId(airline.getId())
                .stream()
                .map(AirCraftMapper::toResponse).toList();
    }

    @Override
    public AircraftResponse updateAirCraft(Long id,AircraftRequest request, Long ownerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()->new Exception("This Owner dont have an Airline")
                );
        Aircraft aircraft=airCraftRepository.findByIdAndAirlineId(id,airline.getId());
        if(aircraft==null)
        {
            throw new Exception("Aircraft Not Found");
        }
        if(request.getCode()!=null
                && !aircraft.getCode().equals(request.getCode())
                && airCraftRepository.existsByCode(request.getCode()))
        {
            throw new Exception("Code Already Exists With Another Aircraft");
        }
        AirCraftMapper.updateEntity(aircraft,request);
        Aircraft savedAirCraft=airCraftRepository.save(aircraft);
        return AirCraftMapper.toResponse(savedAirCraft);
    }

    @Override
    public void deleteAirCraft(Long id, Long ownerId) throws Exception {
        Airline airline=airlineRepository.findByOwnerId(ownerId)
                .orElseThrow(
                        ()->new Exception("This Owner dont have an Airline")
                );
        Aircraft aircraft=airCraftRepository.findByIdAndAirlineId(id,airline.getId());
        if (aircraft==null)
        {
            throw new Exception("AirCraft Does Not Exists");
        }
        airCraftRepository.delete(aircraft);
    }
}