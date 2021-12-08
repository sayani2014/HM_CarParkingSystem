package com.carparkingsystem.carparking.service;

import com.carparkingsystem.carparking.constant.ExceptionType;
import com.carparkingsystem.carparking.dto.ParkingDTO;
import com.carparkingsystem.carparking.dto.ParkingSlotDTO;
import com.carparkingsystem.carparking.exception.ParkingException;
import com.carparkingsystem.carparking.model.Parking;
import com.carparkingsystem.carparking.repository.ParkingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingImpl implements IParking{

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ParkingDTO addParking(ParkingDTO parkingDTO) {
        Parking request = modelMapper.map(parkingDTO, Parking.class);
        Parking parking = parkingRepository.save(request);
        ParkingDTO response = modelMapper.map(parking, ParkingDTO.class);
        return response;
    }

    @Override
    public List<ParkingDTO> getAllParking() {
        return parkingRepository.findAll().stream().map(parking -> {
            return new ParkingDTO(parking.getSpacename(), parking.getCapacity(), parking.getDescription(),
                    parking.getParkingSlot().stream().map(parkingSlot -> {
                        return new ParkingSlotDTO(parkingSlot.getSlotNo(), parkingSlot.isFree());
                    }).collect(Collectors.toList()));
        }).collect(Collectors.toList());
    }

    @Override
    public ParkingDTO getParking(String spacename) {
        Parking userByUserName = checkSpace(spacename);
        ParkingDTO response = modelMapper.map(userByUserName, ParkingDTO.class);
        return response;
    }

    @Override
    public List<Integer> checkParkingLot(String spacename) {
        List<Integer> slot = new ArrayList<>();
        Parking userByUserName = checkSpace(spacename);
        for(int i=0; i < userByUserName.getParkingSlot().size(); i++) {
            System.out.println(userByUserName.getParkingSlot().get(i).isFree());
            if(userByUserName.getParkingSlot().get(i).isFree() == true) {
                slot.add(userByUserName.getParkingSlot().get(i).getSlotNo());
            }
        }
        if(slot.size() < 1) {
            throw new ParkingException(ExceptionType.UNAVAILABLE_SLOT.getMessage());
        }
        return slot;
    }

    private Parking checkSpace(String spacename) {
        return parkingRepository.findBySpacename(spacename).orElseThrow(
                () -> new ParkingException(ExceptionType.UNAUTHORISED_SPACE.getMessage()));
    }
}
