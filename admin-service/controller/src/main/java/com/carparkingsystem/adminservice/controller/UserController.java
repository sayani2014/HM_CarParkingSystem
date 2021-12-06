package com.carparkingsystem.adminservice.controller;

import com.carparkingsystem.adminservice.constants.CommonMessage;
import com.carparkingsystem.adminservice.dto.ResponseDTO;
import com.carparkingsystem.adminservice.dto.UserDTO;
import com.carparkingsystem.adminservice.dto.UserLoginDTO;
import com.carparkingsystem.adminservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/addPerson")
    public ResponseEntity<ResponseDTO> addPerson(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.REGISTRATION_SUCCESSFUL.getMessage(),
                                                userService.addPerson(userDTO)), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<>(new ResponseDTO(CommonMessage.LOGIN_SUCCESSFUL.getMessage(),
                                                userService.login(userLoginDTO)), HttpStatus.OK);
    }
}
