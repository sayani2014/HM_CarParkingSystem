package com.carparkingsystem.userservice.controller;

import com.carparkingsystem.userservice.dto.ParkingDetailsDTO;
import com.carparkingsystem.userservice.dto.ResponseDTO;

import com.carparkingsystem.userservice.dto.UserLoginDTO;
import com.carparkingsystem.userservice.service.IUserService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;
    
    @Mock
    private IUserService userService;

    @Test
    public void getParkingDetailsTest() {
        when(userService.getParkingDetails()).thenReturn(Lists.newArrayList(new ParkingDetailsDTO()));

        ResponseEntity<ResponseDTO> responseEntity = userController.getParkingDetails();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched parking details successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void bookParkingTest() {
        ParkingDetailsDTO parkingDetailsDTO = new ParkingDetailsDTO();
        parkingDetailsDTO.setCarnumber("Abc123");
        parkingDetailsDTO.setSpacename("Test5");
        parkingDetailsDTO.setSlotNo("1");

        when(userService.bookParking(parkingDetailsDTO)).thenReturn(new ParkingDetailsDTO());
        ResponseEntity<ResponseDTO> responseEntity = userController.bookParking(parkingDetailsDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Parking booked successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void userLoginTest() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("sayani.koley");
        userLoginDTO.setPassword("Sayani@123");

        when(userService.login(userLoginDTO)).thenReturn(new ResponseDTO());
        ResponseEntity<ResponseDTO> responseEntity = userController.userLogin(userLoginDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Logged in successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void exitParkingTest() {
        int id = 1;

        when(userService.exitParking(id)).thenReturn(new String());
        ResponseEntity<ResponseDTO> responseEntity = userController.exitParking(id);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Parking exited successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

}
