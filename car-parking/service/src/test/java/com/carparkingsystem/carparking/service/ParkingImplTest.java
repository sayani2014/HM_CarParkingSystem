package com.carparkingsystem.carparking.service;

import com.carparkingsystem.carparking.dto.ParkingDTO;
import com.carparkingsystem.carparking.dto.ParkingSlotDTO;
import com.carparkingsystem.carparking.model.Parking;
import com.carparkingsystem.carparking.model.ParkingSlot;
import com.carparkingsystem.carparking.repository.ParkingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ParkingImplTest {

    @InjectMocks
    private ParkingImpl parkingService;

    @Mock
    private ParkingRepository parkingRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void getAllParkingTest() {
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

        List<ParkingSlot> parkingList = new ArrayList<>();

        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setSlotNo(1);
        parkingSlot1.setFree(true);
        parkingList.add(parkingSlot1);

        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setSlotNo(2);
        parkingSlot2.setFree(true);
        parkingList.add(parkingSlot2);

        List<Parking> parkingList1 = new ArrayList<>();
        Parking parking = new Parking();
        parking.setSpacename("Test6");
        parking.setCapacity(2);
        parking.setDescription("Test");
        parking.setParkingSlot(parkingList);
        parkingList1.add(parking);

        assertNotNull(parking);
        when(parkingRepository.findAll()).thenReturn(parkingList1);
        when(modelMapper.map(parkingList1.get(0), ParkingDTO.class)).thenReturn(parkingDTO);

        List<ParkingDTO> actualParkingData = parkingService.getAllParking();

        assertNotNull(actualParkingData);
        assertEquals("Test6", actualParkingData.get(0).getSpacename());
        assertEquals(2, actualParkingData.get(0).getCapacity());
        assertEquals("Test", actualParkingData.get(0).getDescription());
        assertEquals(2, actualParkingData.get(0).getParkingSlot().size());
    }

    @Test
    public void getParkingTest() {
        String spacename = "Test6";

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

        List<ParkingSlot> parkingList = new ArrayList<>();

        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setSlotNo(1);
        parkingSlot1.setFree(true);
        parkingList.add(parkingSlot1);

        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setSlotNo(2);
        parkingSlot2.setFree(true);
        parkingList.add(parkingSlot2);

        Parking parking = new Parking();
        parking.setSpacename("Test6");
        parking.setCapacity(2);
        parking.setDescription("Test");
        parking.setParkingSlot(parkingList);

        assertNotNull(parking);
        when(parkingRepository.findBySpacename(spacename)).thenReturn(Optional.of(parking));
    }

    @Test
    public void checkParkingLotTest() {
        String spacename = "Test5";
        List<ParkingSlot> parkingList = new ArrayList<>();
        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setSlotNo(1);
        parkingSlot1.setFree(true);
        parkingList.add(parkingSlot1);

        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setSlotNo(2);
        parkingSlot2.setFree(true);
        parkingList.add(parkingSlot2);

        Parking parking = new Parking();
        parking.setSpacename("Test6");
        parking.setCapacity(2);
        parking.setDescription("Test");
        parking.setParkingSlot(parkingList);

        assertNotNull(parking);
        when(parkingRepository.findBySpacename(spacename)).thenReturn(Optional.of(parking));
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

        List<ParkingSlot> parkingList = new ArrayList<>();

        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setSlotNo(1);
        parkingSlot1.setFree(true);
        parkingList.add(parkingSlot1);

        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setSlotNo(2);
        parkingSlot2.setFree(true);
        parkingList.add(parkingSlot2);

        List<Parking> parkingList1 = new ArrayList<>();
        Parking parking = new Parking();
        parking.setSpacename("Test6");
        parking.setCapacity(2);
        parking.setDescription("Test");
        parking.setParkingSlot(parkingList);
        parkingList1.add(parking);

        assertNotNull(parking);
        when(parkingRepository.save(parking)).thenReturn(parking);
        when(modelMapper.map(parkingList1.get(0), ParkingDTO.class)).thenReturn(parkingDTO);
    }

    @Test
    public void updateParkingSlotTest() {
        String spacename = "Test6";
        int slotNo = 1;

        List<ParkingSlotDTO> parkingSlotDTOList = new ArrayList<>();
        ParkingSlotDTO parkingSlotDTO1 = new ParkingSlotDTO();
        parkingSlotDTO1.setSlotNo(1);
        parkingSlotDTO1.setFree(false);
        parkingSlotDTOList.add(parkingSlotDTO1);

        ParkingDTO parkingDTO = new ParkingDTO();
        parkingDTO.setSpacename("Test6");
        parkingDTO.setCapacity(1);
        parkingDTO.setDescription("Test");
        parkingDTO.setParkingSlot(parkingSlotDTOList);

        List<ParkingSlot> parkingList = new ArrayList<>();
        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setSlotNo(1);
        parkingSlot1.setFree(false);
        parkingList.add(parkingSlot1);

        Parking parking = new Parking();
        parking.setSpacename("Test5");
        parking.setCapacity(1);
        parking.setDescription("Test");
        parking.setParkingSlot(parkingList);

        assertNotNull(parking);

        for(int i=0; i<parking.getParkingSlot().size(); i++){
            if(parking.getParkingSlot().get(i).getSlotNo() == slotNo) {
                parking.getParkingSlot().get(i).setFree(true);
                break;
            }
        }

        when(parkingRepository.findBySpacename(spacename)).thenReturn(Optional.of(parking));
        when(parkingRepository.save(parking)).thenReturn(new Parking());
        when(modelMapper.map(parking, ParkingDTO.class)).thenReturn(parkingDTO);
    }

    @Test
    public void updateParkingDetailsTest() {
        String spacename = "Test5";

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

        List<ParkingSlot> parkingList = new ArrayList<>();
        ParkingSlot parkingSlot1 = new ParkingSlot();
        parkingSlot1.setSlotNo(1);
        parkingSlot1.setFree(true);
        parkingList.add(parkingSlot1);

        ParkingSlot parkingSlot2 = new ParkingSlot();
        parkingSlot2.setSlotNo(2);
        parkingSlot2.setFree(true);
        parkingList.add(parkingSlot2);

        Parking parking = new Parking();
        parking.setSpacename("Test5");
        parking.setCapacity(2);
        parking.setDescription("Test");
        parking.setParkingSlot(parkingList);

        assertNotNull(parking);
        when(parkingRepository.findBySpacename(spacename)).thenReturn(Optional.of(parking));
        when(parkingRepository.save(parking)).thenReturn(new Parking());
        when(modelMapper.map(parking, ParkingDTO.class)).thenReturn(parkingDTO);
    }

}
