package com.carparkingsystem.userservice.controller;

import com.carparkingsystem.userservice.constant.CommonMessage;
import com.carparkingsystem.userservice.dto.ParkingDetailsDTO;
import com.carparkingsystem.userservice.dto.ResponseDTO;
import com.carparkingsystem.userservice.dto.UserLoginDTO;
import com.carparkingsystem.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/bookParking")
    public ResponseEntity<ResponseDTO> bookParking (@RequestBody ParkingDetailsDTO userDTO) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.BOOKING_SUCCESSFUL.getMessage(),
                                                                    userService.bookParking(userDTO)), HttpStatus.OK);
    }

    @GetMapping("/getParkingDetails")
    public ResponseEntity<ResponseDTO> getParkingDetails () {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.FETCHED_PARKING_DETAILS.getMessage(),
                userService.getParkingDetails()), HttpStatus.OK);
    }

    @PutMapping("/exitParking")
    public ResponseEntity<ResponseDTO> exitParking (@RequestParam("id") long id) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.PARKING_EXITED.getMessage(),
                userService.exitParking(id)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.LOGIN_SUCCESSFUL.getMessage(),
                userService.login(userLoginDTO)), HttpStatus.OK);
    }
}
