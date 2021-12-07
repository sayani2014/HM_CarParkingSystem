package com.carparkingsystem.adminservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSlotDTO {
    private int slotNo;
    @JsonProperty
    private boolean isFree;
}
