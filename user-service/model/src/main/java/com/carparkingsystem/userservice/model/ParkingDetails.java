package com.carparkingsystem.userservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection="book_parking")
@Data
public class ParkingDetails {

    @Id
    private long id;

    private String carnumber;
    private LocalDateTime entrydatetime;
    private LocalDateTime exitdatetime;
    private String spacename;
    private String slotNo;
}
