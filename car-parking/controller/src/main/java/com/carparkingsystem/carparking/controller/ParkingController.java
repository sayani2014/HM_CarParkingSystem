package com.carparkingsystem.carparking.controller;

import com.carparkingsystem.carparking.constant.CommonMessage;
import com.carparkingsystem.carparking.dto.ParkingDTO;
import com.carparkingsystem.carparking.dto.ResponseDTO;
import com.carparkingsystem.carparking.service.IParking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ParkingController {

    @Autowired
    private IParking parkingService;

    @PostMapping("/addParkingDetails")
    public ResponseEntity<ResponseDTO> addParking (@RequestBody ParkingDTO parkingDTO) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.REGISTRATION_SUCCESSFUL.getMessage(),
                parkingService.addParking(parkingDTO)), HttpStatus.OK);
    }

    @GetMapping("/getAllParkingDetails")
    public ResponseEntity<ResponseDTO> getAllParking () {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.FETCHED_DETAILS_SUCCESSFULLY.getMessage(),
                parkingService.getAllParking()), HttpStatus.OK);
    }

    @GetMapping("/getParkingDetail")
    public ResponseEntity<ResponseDTO> getParking (@RequestParam(value = "spacename") String spacename) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.FETCHED_DETAILS_SUCCESSFULLY.getMessage(),
                parkingService.getParking(spacename)), HttpStatus.OK);
    }

    @GetMapping("/checkParkingLot")
    public ResponseEntity<ResponseDTO> checkParkingLot(@RequestParam(value = "spacename") String spacename) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.FETCHED_DETAILS_SUCCESSFULLY.getMessage(),
                parkingService.checkParkingLot(spacename)), HttpStatus.OK);
    }
}
