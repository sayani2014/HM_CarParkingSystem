package com.carparkingsystem.carparking.service;

import com.carparkingsystem.carparking.dto.ParkingDTO;

import java.util.List;

public interface IParking {
    ParkingDTO addParking(ParkingDTO parkingDTO);
    List<ParkingDTO> getAllParking();
    ParkingDTO getParking(String spacename);
    List<Integer> checkParkingLot(String spacename);
}
