package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.dto.UserDTO;
import com.carparkingsystem.adminservice.dto.UserLoginDTO;

public interface IUserService {
    UserDTO addPerson(UserDTO userDTO);
    UserLoginDTO login(UserLoginDTO userDTO);
}
