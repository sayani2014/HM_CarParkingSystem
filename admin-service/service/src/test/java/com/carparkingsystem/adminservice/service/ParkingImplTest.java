package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.constants.CommonMessage;
import com.carparkingsystem.adminservice.dto.ParkingDTO;
import com.carparkingsystem.adminservice.dto.ParkingSlotDTO;
import com.carparkingsystem.adminservice.dto.ResponseDTO;
import com.carparkingsystem.adminservice.model.Parking;
import com.carparkingsystem.adminservice.model.ParkingSlot;
import com.carparkingsystem.adminservice.repository.UserRepository;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingImplTest {
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RestTemplate restTemplate;

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

        List<ParkingSlot> parkingSlotList = new ArrayList<>();

        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setSlotNo(1);
        parkingSlot1.setFree(true);
        parkingSlotList.add(parkingSlot1);

        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setSlotNo(2);
        parkingSlot2.setFree(true);
        parkingSlotList.add(parkingSlot2);

        Parking parking = new Parking();
        parking.setSpacename("Test6");
        parking.setCapacity(2);
        parking.setDescription("Test");
        parking.setParkingSlot(parkingSlotList);

        when(modelMapper.map(parking, ParkingDTO.class)).thenReturn(parkingDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parkingJsonObject = new JSONObject();
        parkingJsonObject.put("spacename", parkingDTO.getSpacename());
        parkingJsonObject.put("capacity", parkingDTO.getCapacity());
        parkingJsonObject.put("description", parkingDTO.getDescription());
        parkingJsonObject.put("parkingSlot", parkingDTO.getParkingSlot());

        HttpEntity<String> entity = new HttpEntity<>(parkingJsonObject.toString(), headers);
        Mockito.when(restTemplate.exchange("http://localhost:8081/parking-service/addParkingDetails",
                HttpMethod.POST, entity, ResponseDTO.class))
                .thenReturn(new ResponseEntity(CommonMessage.UPDATED_PARKING_DETAILS_SUCCESSFULLY.getMessage(), HttpStatus.OK));
    }

    @Test
    public void getAllParkingTest() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseDTO> entity = new HttpEntity<>(headers);


        Mockito.when(restTemplate.exchange(
                        "http://localhost:8081/parking-service/getAllParkingDetails", HttpMethod.GET, entity,
                        ResponseDTO.class))
                .thenReturn(new ResponseEntity(CommonMessage.FETCHED_DETAILS_SUCCESSFULLY.getMessage(), HttpStatus.OK));
    }

    @Test
    public void getParkingTest() {
        String spacename = "Test5";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseDTO> entity = new HttpEntity<>(headers);

        Mockito.when(restTemplate.exchange(
                        "http://localhost:8081/parking-service/getParkingDetail?spacename=" +spacename, HttpMethod.GET, entity,
                        ResponseDTO.class))
                .thenReturn(new ResponseEntity(CommonMessage.FETCHED_DETAILS_SUCCESSFULLY.getMessage(), HttpStatus.OK));
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

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parkingJsonObject = new JSONObject();
        parkingJsonObject.put("spacename", parkingDTO.getSpacename());
        parkingJsonObject.put("capacity", parkingDTO.getCapacity());
        parkingJsonObject.put("description", parkingDTO.getDescription());
        parkingJsonObject.put("parkingSlot", parkingDTO.getParkingSlot());

        HttpEntity<String> entity = new HttpEntity<>(parkingJsonObject.toString(), headers);
        Mockito.when(restTemplate.exchange("http://localhost:8081/parking-service/updateParkingDetails?spacename="
                        +spacename, HttpMethod.PUT, entity, ResponseDTO.class))
                .thenReturn(new ResponseEntity(CommonMessage.UPDATED_PARKING_DETAILS_SUCCESSFULLY.getMessage(), HttpStatus.OK));
    }
}
