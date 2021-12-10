package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.dto.ParkingDTO;
import com.carparkingsystem.adminservice.dto.ResponseDTO;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class ParkingImpl implements IParking{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ParkingDTO addParking(ParkingDTO parkingDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parkingJsonObject = new JSONObject();
        parkingJsonObject.put("spacename", parkingDTO.getSpacename());
        parkingJsonObject.put("capacity", parkingDTO.getCapacity());
        parkingJsonObject.put("description", parkingDTO.getDescription());
        parkingJsonObject.put("parkingSlot", parkingDTO.getParkingSlot());

        HttpEntity<String> entity = new HttpEntity<>(parkingJsonObject.toString(), headers);
        restTemplate.exchange("http://localhost:8081/parking-service/addParkingDetails",
                                                            HttpMethod.POST, entity, ResponseDTO.class).getBody();

        return parkingDTO;
    }

    @Override
    public ResponseDTO getAllParking() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseDTO> entity = new HttpEntity<>(headers);

        ResponseDTO response =
                restTemplate.exchange(
                        "http://localhost:8081/parking-service/getAllParkingDetails", HttpMethod.GET, entity,
                        ResponseDTO.class).getBody();

        return response;
    }

    @Override
    public ResponseDTO getParking(String spacename) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseDTO> entity = new HttpEntity<>(headers);

        ResponseDTO response =
                restTemplate.exchange(
                        "http://localhost:8081/parking-service/getParkingDetail?spacename=" +spacename, HttpMethod.GET, entity,
                        ResponseDTO.class).getBody();
        return response;
    }

    @Override
    public ResponseDTO updateParkingDetails(String spacename, ParkingDTO parkingDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parkingJsonObject = new JSONObject();
        parkingJsonObject.put("spacename", parkingDTO.getSpacename());
        parkingJsonObject.put("capacity", parkingDTO.getCapacity());
        parkingJsonObject.put("description", parkingDTO.getDescription());
        parkingJsonObject.put("parkingSlot", parkingDTO.getParkingSlot());

        HttpEntity<String> entity = new HttpEntity<>(parkingJsonObject.toString(), headers);
        ResponseDTO response =
                restTemplate.exchange("http://localhost:8081/parking-service/updateParkingDetails?spacename="
                                +spacename, HttpMethod.PUT, entity, ResponseDTO.class).getBody();
        return response;
    }
}
