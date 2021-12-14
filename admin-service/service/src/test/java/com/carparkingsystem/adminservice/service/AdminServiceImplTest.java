package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.dto.UserDTO;
import com.carparkingsystem.adminservice.dto.UserLoginDTO;
import com.carparkingsystem.adminservice.model.User;
import com.carparkingsystem.adminservice.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void addPersonTest() {
        UserDTO user = new UserDTO();
        user.setId(1);
        user.setName("Sayani");
        user.setUsername("sayani.koley");
        user.setPassword("123456789");
        user.setEmail("sayani@abc.com");
        user.setGender("Female");
        user.setDateOfBirth(LocalDate.now());
        user.setPhoneNo("9876543210");
        user.setAddress("Abc");
        user.setCity("Abc");
        user.setCountry("India");
        user.setPin("789560");
        user.setState("Abc");
        user.setRole("User");

        User user1 = new User();
        user1.setId(1);
        user1.setName("Sayani");
        user1.setUsername("sayani.koley");
        user1.setPassword("123456789");
        user1.setEmail("sayani@abc.com");
        user1.setGender("Female");
        user1.setDateOfBirth(LocalDate.now());
        user1.setPhoneNo("9876543210");
        user1.setAddress("Abc");
        user1.setCity("Abc");
        user1.setCountry("India");
        user1.setPin("789560");
        user1.setState("Abc");
        user1.setRole("User");

        when(userRepository.save(user1)).thenReturn(user1);
        when(modelMapper.map(user, UserDTO.class)).thenReturn(user);
    }

    @Test
    public void loginTest() {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setUsername("sayani.koley");
        userLoginDTO.setPassword("Sayani@123");

        User userLogin = new User();
        userLogin.setUsername("sayani.koley");
        userLogin.setPassword("Sayani@123");

        when(userRepository.findByusername(userLogin.getUsername())).thenReturn(Optional.of(userLogin));
    }
}
