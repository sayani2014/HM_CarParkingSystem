package com.carparkingsystem.adminservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;

    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Z][a-zA-Z\\s]+$", message = "Invalid Name")
    private String name;

    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[0-9])(?=[^@#$%^&+=]*[@#$%^&+=][^@#$%^&+=]*$)(?=.*[a-z])(?=.*[A-Z]).{8,}$", message = "Password Invalid")
    private String password;

    @NotNull(message = "Email should not be null")
    @Email(message = "Email invalid")
    private String email;

    @Pattern(regexp = "Male|Female|Others", message = "Gender should be Male, Female or Others")
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(message = "Date of Birth should not be null")
    @Past(message = "Date of Birth should be past date")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Phone Number can not be empty.")
    @Pattern(regexp = "^(6|7|8|9)?[0-9]{9}$")
    private String phoneNo;

    private String address;
    private String city;
    private String state;
    private String country;

    @NotEmpty(message = "Pin can not be empty.")
    @Pattern(regexp = "^[0-9]{6}$")
    private String pin;

    @Pattern(regexp = "User|Admin", message = "Role should be Admin or User")
    private String role;
}
