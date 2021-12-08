package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.dto.UserDTO;
import com.carparkingsystem.adminservice.dto.UserLoginDTO;

import javax.mail.MessagingException;

public interface IAdminService {
    UserDTO addPerson(UserDTO userDTO) throws MessagingException;
    UserLoginDTO login(UserLoginDTO userDTO);
}
