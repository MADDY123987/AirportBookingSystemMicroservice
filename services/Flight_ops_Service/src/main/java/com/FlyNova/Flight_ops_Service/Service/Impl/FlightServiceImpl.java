package com.FlyNova.Flight_ops_Service.Service.Impl;

import com.FlyNova.Flight_ops_Service.Mapper.FlightMapper;
import com.FlyNova.Flight_ops_Service.Model.Flight;
import com.FlyNova.Flight_ops_Service.Repository.FlightRepository;
import com.FlyNova.Flight_ops_Service.Service.FlightService;
import com.FlyNova.enums.FlightStatus;
import com.FlyNova.payload.request.FlightRequest;
import com.FlyNova.payload.response.AircraftResponse;
import com.FlyNova.payload.response.AirlineResponse;
import com.FlyNova.payload.response.AirportResponse;
import com.FlyNova.payload.response.FlightResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    @Override
    public FlightResponse createFlight(Long airlineId, FlightRequest flightRequest) throws Exception {
        if(flightRepository.existsByFlightNumber(flightRequest.getFlightNumber()))
        {
            throw new Exception("Flight Already Exists");
        }
        Flight flight= FlightMapper.toEntity(flightRequest);
        flight.setAirlineId(airlineId);
        Flight saved=flightRepository.save(flight);
        return convertToFlightResponse(saved);
    }


    @Override
    public Page<FlightResponse> getFlightByAirline(Long airlineId,
                                                   Long DepartureAiportId,
                                                   Long ArrivalAirportId,
                                                   Pageable pageable) {
        return flightRepository.findByAirlineId(airlineId,
                DepartureAiportId,
                ArrivalAirportId,
                pageable).map(this::convertToFlightResponse);
    }

    @Override
    public FlightResponse getFlightById(Long id) throws Exception {
        Flight flight=flightRepository.findById(id).orElseThrow(
                ()-> new Exception("Flight not Found With Id")
        );
        return convertToFlightResponse(flight);
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) throws Exception {
        Flight existing=flightRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Not Found With Id")
        );
        if(flightRequest.getFlightNumber()!=null && flightRepository.existsByFlightNumberAndIdNot(flightRequest.getFlightNumber(),id))
        {
            throw new Exception("Flight With Already Exists");
        }
        FlightMapper.updateEntity(flightRequest,existing);
        Flight updated=FlightMapper.toEntity(flightRequest);
        return convertToFlightResponse(updated);
    }

    @Override
    public FlightResponse ChangeStatus(Long id, FlightStatus status) throws Exception {
        Flight existing=flightRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Not Found With id")
        );
        existing.setStatus(status);
        Flight Updated=flightRepository.save(existing);
        return convertToFlightResponse(Updated);
    }

    @Override
    public void deleteFlight(Long airlineId,Long id) throws Exception {
        Flight existing=flightRepository.findByAirlineIdAndId(airlineId,id).orElseThrow(
                ()->new Exception("Flight Not Found With Id")
        );
        flightRepository.delete(existing);
    }

    public FlightResponse convertToFlightResponse(Flight flight)
    {
        AircraftResponse aircraft=AircraftResponse.builder()
                .id(flight.getAirCraftId())
                .build();
        AirlineResponse airline=AirlineResponse.builder()
                        .id(flight.getAirlineId())
                        .build();
        AirportResponse departureAirport=AirportResponse.builder()
                .id(flight.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport= AirportResponse.builder()
                .id(flight.getArrivalAirportId())
                .build();
        return FlightMapper.toResponse(
                flight,
                aircraft,
                airline,
                arrivalAirport,
                departureAirport
        );
    }
}
