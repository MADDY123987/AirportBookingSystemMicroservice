package com.FlyNova.Flight_ops_Service.Service.Impl;

import com.FlyNova.Flight_ops_Service.Mapper.FlightInstanceMapper;
import com.FlyNova.Flight_ops_Service.Model.Flight;
import com.FlyNova.Flight_ops_Service.Model.FlightInstance;
import com.FlyNova.Flight_ops_Service.Repository.FlightInstanceRepository;
import com.FlyNova.Flight_ops_Service.Repository.FlightRepository;
import com.FlyNova.Flight_ops_Service.Service.FlightInstanceService;
import com.FlyNova.Flight_ops_Service.Service.FlightService;
import com.FlyNova.enums.FlightStatus;
import com.FlyNova.payload.request.FlightInstanceRequest;
import com.FlyNova.payload.request.FlightRequest;
import com.FlyNova.payload.response.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class FlightInstanceServiceImpl implements FlightInstanceService {

    private final FlightInstanceRepository flightInstanceRepository;
    private final FlightRepository flightRepository;

    @Override
    public FlightInstanceResponse CreateFlightInstance(Long airlineId, FlightInstanceRequest request) throws Exception {
        Flight flight=flightRepository.findById(request.getFlightId()).orElseThrow(
                ()->new Exception("Flight Not Found")
        );

        AircraftResponse aircraft=AircraftResponse.builder()
                .id(1L)
                .totalSeats(90)
                .build();
        FlightInstance flightInstance= FlightInstanceMapper.toEntity(request,flight);
        flightInstance.setTotalSeats(aircraft.getTotalSeats());
        flightInstance.setAvailableSeats(aircraft.getTotalSeats());

        FlightInstance saved=flightInstanceRepository.save(flightInstance);
        return convertToFlightInstanceResposne(saved);
    }

    @Override
    public FlightInstanceResponse getFlightInstanceById(Long id) throws Exception {
        FlightInstance flightInstance=flightInstanceRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Instance Not Found With Id"+id)
        );
        return convertToFlightInstanceResposne(flightInstance);
    }

    @Override
    public Page<FlightInstanceResponse> getByAirlineId(Long airlineId,
                                                       Long departureAirportId,
                                                       Long arrivalAirportId,
                                                       Long flightId,
                                                       LocalDate onDate,
                                                       Pageable pageable) {
        LocalDateTime start=onDate!=null?onDate.atStartOfDay():null;
        LocalDateTime end=onDate!=null?onDate.plusDays(1).atStartOfDay():null;
        return flightInstanceRepository.findByAirlineIdWithFilters(
                airlineId,departureAirportId,arrivalAirportId,flightId,start,end,pageable
        ).map(this::convertToFlightInstanceResposne);

    }

    @Override
    public FlightInstanceResponse UpdateFlightInstanceById(Long id, FlightInstanceRequest request) throws Exception {
        FlightInstance existing=flightInstanceRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Instance Not Found")
        );
        FlightInstanceMapper.toUpdateEntity(request,existing);
        return convertToFlightInstanceResposne(flightInstanceRepository.save(existing));
    }

    @Override
    public void deleteFlightInstance(Long id) throws Exception {
        FlightInstance existing=flightInstanceRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Instance Not Found")
        );
        flightInstanceRepository.delete(existing);
    }

    private FlightInstanceResponse convertToFlightInstanceResposne(FlightInstance flightInstance)
    {
        AirlineResponse airline= AirlineResponse.builder()
                .id(flightInstance.getAirlineId())
                .build();
        AircraftResponse aircraft=AircraftResponse.builder()
                .id(flightInstance.getFlight().getAirCraftId())
                .build();
        AirportResponse arrivalAirport=AirportResponse.builder()
                .id(flightInstance.getArrivalAirportId())
                .build();
        AirportResponse DepartureAirport=AirportResponse.builder()
                .id(flightInstance.getDepartureAirportId())
                .build();

        return FlightInstanceMapper.toResponse(
                flightInstance,
                airline,
                aircraft,
                arrivalAirport,
                DepartureAirport
        );
    }

}
