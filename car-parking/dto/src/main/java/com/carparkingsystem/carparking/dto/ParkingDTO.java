package com.carparkingsystem.carparking.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDTO {
    private String spacename;
    private long capacity;
    private String description;
    private List<ParkingSlotDTO> parkingSlot;
}
