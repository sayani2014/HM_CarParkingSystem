package com.carparkingsystem.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDetailsDTO {
    private long id;
    private String carnumber;
    private LocalDateTime entrydatetime;
    private LocalDateTime exitdatetime;
    private String spacename;
    private String slotNo;
}
