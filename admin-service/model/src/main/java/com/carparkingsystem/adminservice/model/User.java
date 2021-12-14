package com.carparkingsystem.adminservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "user")
@Data
public class User {
    @Id
    private long id;

    private String name;

    @Indexed(unique = true)
    private String username;

    private String password;

    @Indexed(unique = true)
    private String email;

    private String gender;

    private LocalDate dateOfBirth;

    @Indexed(unique = true)
    private String phoneNo;

    private String address;
    private String city;
    private String state;
    private String country;
    private String pin;

    private String role;

    private String token;
}
