package com.carparkingsystem.carparking.controller;

import com.carparkingsystem.carparking.dto.ParkingDTO;
import com.carparkingsystem.carparking.dto.ParkingSlotDTO;
import com.carparkingsystem.carparking.dto.ResponseDTO;
import com.carparkingsystem.carparking.service.IParking;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingControllerTest {
    @InjectMocks
    private ParkingController parkingController;

    @Mock
    private IParking parkingService;

    @Test
    public void getAllParkingTest() {
        when(parkingService.getAllParking()).thenReturn(Lists.newArrayList(new ParkingDTO()));

        ResponseEntity<ResponseDTO> responseEntity = parkingController.getAllParking();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched Parking Details Successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void getParkingTest() {
        String spacename = "Test5";
        when(parkingService.getParking(spacename)).thenReturn(new ParkingDTO());

        ResponseEntity<ResponseDTO> responseEntity = parkingController.getParking(spacename);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched Parking Details Successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void checkParkingLotTest() {
        String spacename = "Test5";
        when(parkingService.checkParkingLot(spacename)).thenReturn(Lists.newArrayList());

        ResponseEntity<ResponseDTO> responseEntity = parkingController.checkParkingLot(spacename);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Fetched Parking Details Successfully.", responseEntity.getBody().getMessage());
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
        ResponseEntity<ResponseDTO> responseEntity = parkingController.addParking(parkingDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Registration Successful.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }

    @Test
    public void updateParkingSlotTest() {
        String spacename = "Test6";
        int slotNo = 1;

        when(parkingService.updateParkingSlot(spacename, slotNo)).thenReturn(new ParkingDTO());
        ResponseEntity<ResponseDTO> responseEntity = parkingController.updateParkingSlot(spacename, slotNo);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated Parking Slot Successfully.", responseEntity.getBody().getMessage());
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

        when(parkingService.updateParkingDetails(spacename, parkingDTO)).thenReturn(new ParkingDTO());
        ResponseEntity<ResponseDTO> responseEntity = parkingController.updateParkingDetails(spacename, parkingDTO);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Updated Parking Details Successfully.", responseEntity.getBody().getMessage());
        assertNotNull(responseEntity.getBody().getObj());
    }
}
