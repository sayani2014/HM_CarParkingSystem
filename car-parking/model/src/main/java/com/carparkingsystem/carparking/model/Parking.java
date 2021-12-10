package com.carparkingsystem.carparking.model;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection="parking")
@Data
public class Parking {
    @Indexed(unique = true)
    private String spacename;
    private long capacity;
    private String description;
    private List<ParkingSlot> parkingSlot;
}
