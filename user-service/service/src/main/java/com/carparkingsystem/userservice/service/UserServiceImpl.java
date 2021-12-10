package com.carparkingsystem.userservice.service;

import com.carparkingsystem.userservice.constant.CommonMessage;
import com.carparkingsystem.userservice.constant.ExceptionType;
import com.carparkingsystem.userservice.dto.ParkingDetailsDTO;
import com.carparkingsystem.userservice.dto.ResponseDTO;
import com.carparkingsystem.userservice.dto.UserLoginDTO;
import com.carparkingsystem.userservice.exception.UserException;
import com.carparkingsystem.userservice.model.ParkingDetails;
import com.carparkingsystem.userservice.repository.UserRepository;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements IUserService {

    private static final String HOSTING_SEQ_KEY = "hosting";

    @Autowired
    private ISequence sequence;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ParkingDetailsDTO bookParking(ParkingDetailsDTO parkingDetailsDTO) {
        parkingDetailsDTO.setId(sequence.getNextSequenceId(HOSTING_SEQ_KEY));
        parkingDetailsDTO.setEntrydatetime(LocalDateTime.now());

        boolean checkStatus = checkExistingRecord(parkingDetailsDTO.getEntrydatetime());

        if(checkStatus == false) {
            ResponseDTO response = checkParkingLot(parkingDetailsDTO.getSpacename());
            List<String> emptySpots = new ArrayList<>();
            List<String> responseData = Stream.of(response.getObj()).map(Object::toString).collect(Collectors.toList());
            for (String data : responseData)
                for (String s : data.replace("[","").replace("]","").split(","))
                    emptySpots.add(s.trim());

            ParkingDetails parkingDetails = saveUserData(parkingDetailsDTO, emptySpots);
            updateParkingSlot(parkingDetails.getSpacename(), parkingDetails.getSlotNo());
            ParkingDetailsDTO finalResponse = modelMapper.map(parkingDetails, ParkingDetailsDTO.class);
            return finalResponse;
        }
        return null;
    }

    @Override
    public List<ParkingDetailsDTO> getParkingDetails() {
        return userRepository.findAll().stream().map(parking -> {
            return new ParkingDetailsDTO(parking.getId(), parking.getCarnumber(), parking.getEntrydatetime(),
                    parking.getExitdatetime(), parking.getSpacename(), parking.getSlotNo());
        }).collect(Collectors.toList());
    }

    @Override
    public String exitParking(long id) {
        Optional<ParkingDetails> byId = userRepository.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseDTO> entity = new HttpEntity<>(headers);

        restTemplate.exchange("http://localhost:8081/parking-service/updateParkingSlot?spacename="
                        +byId.get().getSpacename() +"&slotNo="+byId.get().getSlotNo(), HttpMethod.PUT, entity,
                ResponseDTO.class).getBody();

        return CommonMessage.PARKING_EXITED.getMessage();
    }

    @Override
    public ResponseDTO login(UserLoginDTO userLoginDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject parkingJsonObject = new JSONObject();
        parkingJsonObject.put("username", userLoginDTO.getUsername());
        parkingJsonObject.put("password", userLoginDTO.getPassword());

        HttpEntity<String> entity = new HttpEntity<>(parkingJsonObject.toString(), headers);
        ResponseDTO responseDTO = restTemplate.exchange("http://localhost:8080/admin-service/login",
                HttpMethod.POST, entity, ResponseDTO.class).getBody();

        return responseDTO;
    }

    private boolean checkExistingRecord(LocalDateTime entrydatetime) {
        boolean flag = false;
        List<ParkingDetails> byEntrydatetime = userRepository.findByEntrydatetime(entrydatetime);
        for(int i=0; i<byEntrydatetime.size(); i++) {
            if(byEntrydatetime.get(i).getExitdatetime().isAfter(LocalDateTime.now())) {
                flag = true;
                throw new UserException(ExceptionType.PARKING_EXIST.getMessage());
            }
        }
        return flag;
    }

    private ResponseDTO checkParkingLot(String spaceName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseDTO> entity = new HttpEntity<>(headers);

        ResponseDTO response =
                restTemplate.exchange("http://localhost:8081/parking-service/checkParkingLot?spacename="
                                +spaceName, HttpMethod.GET, entity,
                        ResponseDTO.class).getBody();

        return response;
    }

    private ParkingDetails saveUserData(ParkingDetailsDTO parkingDetailsDTO, List<String> emptySpots) {
        for(int i=0; i<emptySpots.size(); i++) {
            if(emptySpots.get(i).contains(parkingDetailsDTO.getSlotNo())) {
                ParkingDetails parkingRequest = modelMapper.map(parkingDetailsDTO, ParkingDetails.class);
                ParkingDetails parkingDetails = userRepository.save(parkingRequest);
                return parkingDetails;
            } else {
                throw new UserException(ExceptionType.PARKING_LOT_FULL.getMessage());
            }
        }
        return null;
    }

    private void updateParkingSlot(String spacename, String slotno) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<ResponseDTO> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                "http://localhost:8081/parking-service/updateParkingSlot?spacename="+spacename+
                        "&slotNo="+slotno, HttpMethod.PUT, entity,
                ResponseDTO.class).getBody();
    }
}
