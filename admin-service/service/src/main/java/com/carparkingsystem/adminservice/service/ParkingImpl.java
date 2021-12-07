package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.dto.ParkingDTO;
import com.carparkingsystem.adminservice.dto.ResponseDTO;
import com.carparkingsystem.adminservice.model.Parking;
import com.carparkingsystem.adminservice.repository.ParkingRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ParkingImpl implements IParking{

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public ParkingDTO addParking(ParkingDTO parkingDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ParkingDTO parkingDTO1 = mapper.convertValue(parkingDTO, ParkingDTO.class);

        HttpEntity<String> entity = new HttpEntity<>(parkingDTO1.toString(), headers);

        restTemplate.exchange("http://localhost:8081/parking-service/addParkingDetails",
                HttpMethod.POST, entity, ResponseDTO.class).getBody();

        return parkingDTO;
    }

    @Override
    public List<ParkingDTO> getAllParking() {
        return null;
    }

    @Override
    public ParkingDTO getParking(String spacename) {
        return null;
    }
}
