package com.carparkingsystem.adminservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence")
@Data
public class SequenceID {
    @Id
    private String id;
    private long seq;
}
