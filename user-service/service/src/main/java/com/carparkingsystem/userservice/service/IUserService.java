package com.carparkingsystem.userservice.service;

import com.carparkingsystem.userservice.dto.ParkingDetailsDTO;

import java.util.List;

public interface IUserService {
    ParkingDetailsDTO bookParking(ParkingDetailsDTO userDTO);
    List<ParkingDetailsDTO> getParkingDetails();
}
