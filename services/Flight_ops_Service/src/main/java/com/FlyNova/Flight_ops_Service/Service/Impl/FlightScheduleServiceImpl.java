package com.FlyNova.Flight_ops_Service.Service.Impl;

import com.FlyNova.Flight_ops_Service.Mapper.FlightInstanceMapper;
import com.FlyNova.Flight_ops_Service.Mapper.FlightScheduleMapper;
import com.FlyNova.Flight_ops_Service.Model.Flight;
import com.FlyNova.Flight_ops_Service.Model.FlightSchedule;
import com.FlyNova.Flight_ops_Service.Repository.FlightRepository;
import com.FlyNova.Flight_ops_Service.Repository.FlightScheduleRepository;
import com.FlyNova.Flight_ops_Service.Service.FlightInstanceService;
import com.FlyNova.Flight_ops_Service.Service.FlightScheduleService;
import com.FlyNova.enums.FlightStatus;
import com.FlyNova.payload.request.FlightInstanceRequest;
import com.FlyNova.payload.request.FlightScheduleRequest;
import com.FlyNova.payload.response.AirportResponse;
import com.FlyNova.payload.response.FlightScheduleResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@AllArgsConstructor
public class FlightScheduleServiceImpl implements FlightScheduleService {
    private final FlightRepository flightRepository;
    private final FlightScheduleRepository flightScheduleRepository;
    private final FlightInstanceService flightInstanceService;

    @Override
    public FlightScheduleResponse createFlightSchedule(Long AirlineId, FlightScheduleRequest request) throws Exception {
        //Todo Watch for the airlineId
        Flight flight= flightRepository.findById(request.getFlightId())
                .orElseThrow(()->new Exception("Flight Not Found With ID"+AirlineId));

        if(request.getEndDate().isBefore(request.getStartDate()))
        {
            throw new Exception("End Date is Before the Start Date");
        }
        FlightSchedule schedule= FlightScheduleMapper.toEntity(request,flight);
        FlightSchedule savedSchedule=flightScheduleRepository.save(schedule);

        List<DayOfWeek>operatingDays=savedSchedule.getOperatingDays();
        LocalDate startDate=savedSchedule.getStartDate();
        LocalDate endDate=savedSchedule.getEndDate();

        FlightInstanceRequest flightInstanceRequest=FlightInstanceRequest.builder()
                .scheduleId(savedSchedule.getId())
                .flightId(flight.getId())
                .departureAirportId(schedule.getDepartureAirportId())
                .ArrivalAirportId(schedule.getArrivalAirportId())
                .status(FlightStatus.SCHEDULED)
                .build();

        for(LocalDate date=startDate;!date.isAfter(endDate);date=date.plusDays(1))
        {
            if(operatingDays.contains(date.getDayOfWeek()))
            {
                flightInstanceRequest.setDepartureDateTime(
                        LocalDateTime.of(date,savedSchedule.getDepartureTime()));
                flightInstanceRequest.setArrivalDateTime(
                        LocalDateTime.of(date,savedSchedule.getArrivalTime()));
                System.out.println();
                flightInstanceService.CreateFlightInstance(
                        AirlineId,flightInstanceRequest);
            }
        }
        return convertToFlightScheduleResponse(savedSchedule);
    }

    @Override
    public FlightScheduleResponse getFlightScheduleById(Long id) throws Exception {
        FlightSchedule flightSchedule=flightScheduleRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Schedule Not Found With Id")
        );
        return convertToFlightScheduleResponse(flightSchedule);
    }

    @Override
    public List<FlightScheduleResponse> getFlightScheduleByAirline(Long airlineId) {
        //todoWatch AirlineId
        List<FlightSchedule> schedules=flightScheduleRepository.findFlightAirlineById(airlineId);

        return schedules.stream().map(
                this::convertToFlightScheduleResponse
        ).toList();
    }

    @Override
    public FlightScheduleResponse updateFlightSchedule(Long id, FlightScheduleRequest request) throws Exception {
        FlightSchedule flightSchedule=flightScheduleRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Scedule Not Found With Id")
        );
        FlightScheduleMapper.updateEntity(request,flightSchedule);
        FlightSchedule updatedSchedule=flightScheduleRepository.save(flightSchedule);
        return convertToFlightScheduleResponse(updatedSchedule);
    }

    @Override
    public void deleteFlightSchedule(Long id) throws Exception {
        FlightSchedule flightSchedule=flightScheduleRepository.findById(id).orElseThrow(
                ()->new Exception("Flight Scedule Not Found With Id")
        );
        flightScheduleRepository.delete(flightSchedule);
    }
    private FlightScheduleResponse convertToFlightScheduleResponse(FlightSchedule flightSchedule)
    {
        //todo-Service to Service Communication
        AirportResponse departureAirport=AirportResponse.builder()
                .id(flightSchedule.getDepartureAirportId())
                .build();
        AirportResponse arrivalAirport=AirportResponse.builder()
                .id(flightSchedule.getArrivalAirportId())
                .build();
        return FlightScheduleMapper.toResponse(
                flightSchedule,arrivalAirport,departureAirport
        );
    }
}
