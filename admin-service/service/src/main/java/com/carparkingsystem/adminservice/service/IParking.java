package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.dto.ParkingDTO;

import java.util.List;

public interface IParking {
    ParkingDTO addParking(ParkingDTO parkingDTO);
    List<ParkingDTO> getAllParking();
    ParkingDTO getParking(String spacename);
}
