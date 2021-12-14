package com.carparkingsystem.adminservice.controller;

import com.carparkingsystem.adminservice.dto.*;
import com.carparkingsystem.adminservice.model.User;
import com.carparkingsystem.adminservice.service.IAdminService;
import com.carparkingsystem.adminservice.service.IParking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController adminController;

    @Mock
    private IAdminService adminService;

    @Mock
    private IParking parkingService;

    @Test
    public void getAllParkingTest() {
        when(parkingService.getAllParking()).thenReturn(new ResponseDTO());

        ResponseEntity<ResponseDTO> responseEntity = adminController.getAllParking();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched All Parking Slot Details.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void getParkingTest() {
        String spacename = "Test5";
        when(parkingService.getParking(spacename)).thenReturn(new ResponseDTO());

        ResponseEntity<ResponseDTO> responseEntity = adminController.getParking(spacename);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched All Parking Slot Details.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void userLoginTest() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("sayani.koley");
        userLoginDTO.setPassword("Sayani@123");

        when(adminService.login(userLoginDTO)).thenReturn(new UserLoginDTO());
        ResponseEntity<ResponseDTO> responseEntity = adminController.userLogin(userLoginDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Login Successful.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void addPersonTest() throws MessagingException {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setName("Sayani");
        user.setUsername("sayani.koley");
        user.setPassword("123456789");
        user.setEmail("sayani@abc.com");
        user.setGender("Female");
        user.setDateOfBirth(LocalDate.now());
        user.setPhoneNo("9876543210");
        user.setAddress("Abc");
        user.setCity("Abc");
        user.setCountry("India");
        user.setPin("789560");
        user.setState("Abc");
        user.setRole("User");

        User user1 = new User();
        user1.setId(1);
        user1.setName("Sayani");
        user1.setUsername("sayani.koley");
        user1.setPassword("123456789");
        user1.setEmail("sayani@abc.com");
        user1.setGender("Female");
        user1.setDateOfBirth(LocalDate.now());
        user1.setPhoneNo("9876543210");
        user1.setAddress("Abc");
        user1.setCity("Abc");
        user1.setCountry("India");
        user1.setPin("789560");
        user1.setState("Abc");
        user1.setRole("User");

        when(adminService.addPerson(user)).thenReturn(new UserDTO());
        ResponseEntity<ResponseDTO> responseEntity = adminController.addPerson(user);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Registration Successful.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void addParkingTest() {
        List<ParkingSlotDTO> parkingSlotDTOList = new ArrayList<>();

        ParkingSlotDTO parkingSlotDTO1 = new ParkingSlotDTO();
        parkingSlotDTO1.setSlotNo(1);
        parkingSlotDTO1.setFree(true);
        parkingSlotDTOList.add(parkingSlotDTO1);

        ParkingSlotDTO parkingSlotDTO2 = new ParkingSlotDTO();
        parkingSlotDTO2.setSlotNo(2);
        parkingSlotDTO2.setFree(true);
        parkingSlotDTOList.add(parkingSlotDTO2);

        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setSpacename("Test6");
        parkingDTO.setCapacity(2);
        parkingDTO.setDescription("Test");
        parkingDTO.setParkingSlot(parkingSlotDTOList);

        when(parkingService.addParking(parkingDTO)).thenReturn(new ParkingDTO());
        ResponseEntity<ResponseDTO> responseEntity = adminController.addParking(parkingDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Registration Successful.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void updateParkingDetailsTest() {
        String spacename = "Test6";
        List<ParkingSlotDTO> parkingSlotDTOList = new ArrayList<>();

        ParkingSlotDTO parkingSlotDTO1 = new ParkingSlotDTO();
        parkingSlotDTO1.setSlotNo(1);
        parkingSlotDTO1.setFree(true);
        parkingSlotDTOList.add(parkingSlotDTO1);

        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setSpacename("Test6");
        parkingDTO.setCapacity(1);
        parkingDTO.setDescription("Testing Update");
        parkingDTO.setParkingSlot(parkingSlotDTOList);

        when(parkingService.updateParkingDetails(spacename, parkingDTO)).thenReturn(new ResponseDTO());
        ResponseEntity<ResponseDTO> responseEntity = adminController.updateParkingDetails(spacename, parkingDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated Parking Details Successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }
}
