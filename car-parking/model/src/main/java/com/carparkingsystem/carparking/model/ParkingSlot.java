package com.carparkingsystem.carparking.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class ParkingSlot {
    private int slotNo;
    private boolean isFree;
}
