package com.FlyNova.Seat_service.Service.Impl;

import com.FlyNova.Seat_service.Mapper.CabinClassMapper;
import com.FlyNova.Seat_service.Model.CabinClass;
import com.FlyNova.Seat_service.Repository.CabinClassRepository;
import com.FlyNova.Seat_service.Service.CabinClassService;
import com.FlyNova.enums.CabinClassType;
import com.FlyNova.payload.request.CabinClassRequest;
import com.FlyNova.payload.response.CabinClassResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CabinClassServiceImpl implements CabinClassService {
    private final CabinClassRepository cabinClassRepository;

    @Override
    public CabinClassResponse createCabinClass(CabinClassRequest request) throws Exception {
        if(cabinClassRepository.existsByCodeAndAircraftId(request.getCode(),request.getAircraftId())) {
            throw new Exception("Cabin Class with code Already Exists");
        }
        CabinClass cabinClass= CabinClassMapper.toEntity(request);
        CabinClass savedCabin=cabinClassRepository.save(cabinClass);
        return CabinClassMapper.toResponse(savedCabin,null);
    }

    @Override
    public CabinClassResponse getCabinClassById(Long id) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(id).orElseThrow(
                ()->new Exception("Cabin Class not Found With Id")
        );
        return CabinClassMapper.toResponse(cabinClass,cabinClass.getSeatMap());
    }

    @Override
    public List<CabinClassResponse> getCabinClassesByAircraftId(Long aircraftId) {
        return cabinClassRepository.findByAircraftId(aircraftId)
                .stream()
                .map(cc->CabinClassMapper.toResponse(cc,cc.getSeatMap()))
                .collect(Collectors.toList());
    }

    @Override
    public CabinClassResponse getAirCraftIdAndName(Long aircraftId, CabinClassType name) {
        CabinClass cabinClass=cabinClassRepository.findByAircraftIdAndName(aircraftId, name);
        return CabinClassMapper.toResponse(cabinClass,null);
    }

    @Override
    public CabinClassResponse updateCabinClass(Long id, CabinClassRequest request) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(id).orElseThrow(
                ()->new Exception("Cabin Class Not Found with Id")
        );
        if(cabinClassRepository.existsByCodeAndAircraftIdAndIdNot(
                request.getCode().toUpperCase(),
                cabinClass.getAircraftId(),
                cabinClass.getId()))
        {
            throw new Exception("Cabin Class With Code Already Exists");
        }
        CabinClassMapper.updateEntity(request,cabinClass);
        CabinClass updated=cabinClassRepository.save(cabinClass);
        return CabinClassMapper.toResponse(cabinClass,updated.getSeatMap());
    }

    @Override
    public void deleteCabinClass(Long id) throws Exception {
        CabinClass cabinClass=cabinClassRepository.findById(id).orElseThrow(
                ()->new Exception("Cabin Class Not Found With Id")
        );
        cabinClassRepository.delete(cabinClass);
    }
}
