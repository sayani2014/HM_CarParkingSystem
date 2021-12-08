package com.carparkingsystem.userservice.controller;

import com.carparkingsystem.userservice.constant.CommonMessage;
import com.carparkingsystem.userservice.dto.ParkingDetailsDTO;
import com.carparkingsystem.userservice.dto.ResponseDTO;
import com.carparkingsystem.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/book-parking")
    public ResponseEntity<ResponseDTO> bookParking (@RequestBody ParkingDetailsDTO userDTO) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.BOOKING_SUCCESSFUL.getMessage(),
                                                                    userService.bookParking(userDTO)), HttpStatus.OK);
    }

    @GetMapping("/getParkingDetails")
    public ResponseEntity<ResponseDTO> getParkingDetails () {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.BOOKING_SUCCESSFUL.getMessage(),
                userService.getParkingDetails()), HttpStatus.OK);
    }

}
