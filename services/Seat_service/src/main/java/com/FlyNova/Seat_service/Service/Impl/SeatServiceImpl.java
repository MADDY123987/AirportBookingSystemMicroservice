package com.FlyNova.Seat_service.Service.Impl;

import com.FlyNova.Seat_service.Mapper.SeatMapper;
import com.FlyNova.Seat_service.Model.CabinClass;
import com.FlyNova.Seat_service.Model.Seat;
import com.FlyNova.Seat_service.Model.SeatMap;
import com.FlyNova.Seat_service.Repository.CabinClassRepository;
import com.FlyNova.Seat_service.Repository.SeatMapRepository;
import com.FlyNova.Seat_service.Repository.SeatRepository;
import com.FlyNova.Seat_service.Service.SeatService;
import com.FlyNova.enums.SeatType;
import com.FlyNova.payload.request.SeatRequest;
import com.FlyNova.payload.response.SeatResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final SeatMapRepository seatMapRepository;
    private final CabinClassRepository cabinClassRepository;

    @Override
    public void generateSeats(Long seatMapId) throws Exception {
       boolean exists = seatRepository.existsBySeatMapId(seatMapId);
       if(exists)
       {
           throw new Exception("Seat already Created for Seat Map");
       }
        SeatMap seatMap=seatMapRepository.findById(seatMapId).orElseThrow(
                ()->new Exception("Seat Map Not Found")
        );
       int leftSeatsPerRow=seatMap.getLeftSeatsPerRow();
       int rightSeatsPerRow=seatMap.getRightSeatsPerRow();
       int rows=seatMap.getTotalRows();
       int seatperRow=leftSeatsPerRow+rightSeatsPerRow;

       List<Seat> seats=new ArrayList<>();

       for(int row=1;row<=rows;row++)
       {
           for(int col=0;col<seatperRow;col++)
           {
               String seatNum=row+getSeatLetter(col);
               SeatType type=getSeatType(col,leftSeatsPerRow,rightSeatsPerRow);
               Seat seat=Seat.builder()
                       .seatNumber(seatNum)
                       .seatRow(row)
                       .columnLetter(getSeatLetter(col).charAt(0))
                       .seatType(type)
                       .seatMap(seatMap)
                       .build();
               seats.add(seat);
           }
       }
       seatRepository.saveAll(seats);
    }

    private SeatType getSeatType(int col, int leftSeatsPerRow, int rightSeatsPerRow) {
        int totalSeats=leftSeatsPerRow+rightSeatsPerRow;
        if(col==0||col==totalSeats-1) return SeatType.Window;
        //left aisle
        if(col==leftSeatsPerRow-1) return SeatType.AISLE;
        //right aisle
        if(col==rightSeatsPerRow-1) return SeatType.AISLE;
        return SeatType.MIDDLE;
    }

    private String getSeatLetter(int col) {
        StringBuilder sb=new StringBuilder();
        while (col>=0){
            sb.insert(0,(char)('A'+(col%26)));
            col=col/26-1;
        }
        return sb.toString();
    }
    //0->A,1->B,2->C,3->D,4->D,5->E
    //col=27 --> 27/26=1=>B then sb=B --> col=27/26-1=>col=1-1=>0 0%26=0=>A then sb=AB -->col=0%26-1=>col=0-1=>col=-1
    @Override
    public List<SeatResponse> getAll() {
        return seatRepository.findAll()
                .stream().map(SeatMapper::toResponse)
                .collect(Collectors.toList());
    }
    @Override
    public SeatResponse updateSeats(Long id, SeatRequest request) {
        Seat existing = seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat not found with id: " + id));

        SeatMap seatMap = seatMapRepository.findById(request.getSeatMapId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Seat map not found with id: " + request.getSeatMapId()));

        CabinClass cabinClass = null;
        if (request.getCabinClassId() != null) {
            cabinClass = cabinClassRepository.findById(request.getCabinClassId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Cabin class not found with id: " + request.getCabinClassId()));
        }

        SeatMapper.updateEntity(request, existing, seatMap, cabinClass);
        Seat saved = seatRepository.save(existing);
        return SeatMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SeatResponse getSeatById(Long id) {
        Seat seat = seatRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Seat not found with id: " + id));
        return SeatMapper.toResponse(seat);
    }


}
