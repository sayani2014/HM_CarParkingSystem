package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.dto.ParkingDTO;
import com.carparkingsystem.adminservice.dto.ResponseDTO;

import java.util.List;

public interface IParking {
    ParkingDTO addParking(ParkingDTO parkingDTO);
    ResponseDTO getAllParking();
    ResponseDTO getParking(String spacename);
    ResponseDTO updateParkingDetails(String spacename, ParkingDTO parkingDTO);
}
