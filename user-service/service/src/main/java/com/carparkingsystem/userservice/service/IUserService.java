package com.carparkingsystem.userservice.service;

import com.carparkingsystem.userservice.dto.ParkingDetailsDTO;
import com.carparkingsystem.userservice.dto.ResponseDTO;
import com.carparkingsystem.userservice.dto.UserLoginDTO;

import java.util.List;

public interface IUserService {
    ParkingDetailsDTO bookParking(ParkingDetailsDTO userDTO);
    List<ParkingDetailsDTO> getParkingDetails();
    String exitParking(long id);
    ResponseDTO login(UserLoginDTO userLoginDTO);
}
