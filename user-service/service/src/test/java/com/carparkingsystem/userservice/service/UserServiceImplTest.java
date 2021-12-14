package com.carparkingsystem.userservice.service;

import com.carparkingsystem.userservice.constant.CommonMessage;
import com.carparkingsystem.userservice.dto.ParkingDetailsDTO;
import com.carparkingsystem.userservice.dto.ResponseDTO;
import com.carparkingsystem.userservice.dto.UserLoginDTO;
import com.carparkingsystem.userservice.model.ParkingDetails;
import com.carparkingsystem.userservice.repository.UserRepository;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getParkingDetails() {
        List<ParkingDetails> parkingDetails = new ArrayList<>();

        ParkingDetails parkingDetails1 = new ParkingDetails();
        parkingDetails1.setId(1);
        parkingDetails1.setCarnumber("Abc123");
        parkingDetails1.setSpacename("Test5");
        parkingDetails1.setSlotNo("1");
        parkingDetails.add(parkingDetails1);

        ParkingDetails parkingDetails2 = new ParkingDetails();
        parkingDetails2.setId(2);
        parkingDetails2.setCarnumber("WB123");
        parkingDetails2.setSpacename("Test5");
        parkingDetails2.setSlotNo("2");
        parkingDetails.add(parkingDetails2);

        ParkingDetailsDTO parkingDetailsDTO1 = new ParkingDetailsDTO();
        parkingDetailsDTO1.setId(1);
        parkingDetailsDTO1.setCarnumber("Abc123");
        parkingDetailsDTO1.setSpacename("Test5");
        parkingDetailsDTO1.setSlotNo("1");

        ParkingDetailsDTO parkingDetailsDTO2 = new ParkingDetailsDTO();
        parkingDetailsDTO2.setId(2);
        parkingDetailsDTO2.setCarnumber("WB123");
        parkingDetailsDTO2.setSpacename("Test5");
        parkingDetailsDTO2.setSlotNo("2");

        assertNotNull(parkingDetails);
        when(userRepository.findAll()).thenReturn(parkingDetails);
        when(modelMapper.map(parkingDetails.get(0), ParkingDetailsDTO.class)).thenReturn(parkingDetailsDTO1);
        when(modelMapper.map(parkingDetails.get(1), ParkingDetailsDTO.class)).thenReturn(parkingDetailsDTO2);

        List<ParkingDetailsDTO> actualParkingDetails = userService.getParkingDetails();
        assertNotNull(actualParkingDetails);

        for (int i = 0; i < parkingDetails.size(); i++) {
            assertEquals(i+1, actualParkingDetails.get(i).getId());
        }

        assertEquals("Abc123", actualParkingDetails.get(0).getCarnumber());
        assertEquals("Test5", actualParkingDetails.get(0).getSpacename());
        assertEquals("1", actualParkingDetails.get(0).getSlotNo());

        assertEquals("WB123", actualParkingDetails.get(1).getCarnumber());
        assertEquals("Test5", actualParkingDetails.get(1).getSpacename());
        assertEquals("2", actualParkingDetails.get(1).getSlotNo());
    }

    @Test
    public void bookParkingTest() {
        ParkingDetails parkingDetails1 = new ParkingDetails();
        parkingDetails1.setCarnumber("Abc123");
        parkingDetails1.setSpacename("Test5");
        parkingDetails1.setSlotNo("1");

        ParkingDetailsDTO parkingDetailsDTO1 = new ParkingDetailsDTO();
        parkingDetailsDTO1.setCarnumber("Abc123");
        parkingDetailsDTO1.setSpacename("Test5");
        parkingDetailsDTO1.setSlotNo("1");

        when(userRepository.save(parkingDetails1)).thenReturn(parkingDetails1);
        when(modelMapper.map(parkingDetails1, ParkingDetailsDTO.class)).thenReturn(parkingDetailsDTO1);
    }

    @Test
    public void loginTest() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("sayani.koley");
        userLoginDTO.setPassword("Sayani@123");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parkingJsonObject = new JSONObject();
        parkingJsonObject.put("username", userLoginDTO.getUsername());
        parkingJsonObject.put("password", userLoginDTO.getPassword());
        HttpEntity<String> entity = new HttpEntity<>(parkingJsonObject.toString(), headers);
        Mockito.when(restTemplate.exchange("http://localhost:8080/admin-service/login",
                        HttpMethod.POST, entity, ResponseDTO.class)).thenReturn(new ResponseEntity(userLoginDTO, HttpStatus.OK));

    }

    @Test
    public void exitParking() {
        String slotNo = "1";
        String spaceName = "Test5";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Mockito.when(restTemplate.exchange("http://localhost:8081/parking-service/updateParkingSlot?spacename="
                        +spaceName +"&slotNo="+slotNo, HttpMethod.PUT, entity,
                ResponseDTO.class)).thenReturn(new ResponseEntity(CommonMessage.PARKING_EXITED.getMessage(), HttpStatus.OK));
    }
}


