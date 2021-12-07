package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.constants.ExceptionType;
import com.carparkingsystem.adminservice.dto.UserDTO;
import com.carparkingsystem.adminservice.dto.UserLoginDTO;
import com.carparkingsystem.adminservice.exception.UserException;
import com.carparkingsystem.adminservice.model.User;
import com.carparkingsystem.adminservice.repository.UserRepository;
import com.carparkingsystem.adminservice.utils.MailUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService{

    private static final String HOSTING_SEQ_KEY = "hosting";

    @Autowired
    private ISequence sequence;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailUtil mailUtil;

    @Override
    public UserDTO addPerson(UserDTO userDTO) throws MessagingException {
        Optional<User> personDetails = userRepository.findByEmail(userDTO.getEmail());
        if (personDetails.isPresent()) {
            throw new UserException(ExceptionType.USER_ALREADY_EXIST.getMessage());
        }
        User userData = saveUserData(userDTO);
        UserDTO response = modelMapper.map(userData, UserDTO.class);
        mailUtil.sendEmail(response);
        return response;
    }

    private User saveUserData(UserDTO userDTO) {
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userDTO.setId(sequence.getNextSequenceId(HOSTING_SEQ_KEY));
        User userRequest = modelMapper.map(userDTO, User.class);
        User userData = userRepository.save(userRequest);
        return userData;
    }

    @Override
    public UserLoginDTO login(UserLoginDTO userLoginDTO) {
        User userByUserName = userRepository.findByusername(userLoginDTO.getUsername()).orElseThrow(
                () -> new UserException(ExceptionType.UNAUTHORISED_USER.getMessage()));
        boolean password = bCryptPasswordEncoder.matches(userLoginDTO.getPassword(), userByUserName.getPassword());
        if (!password) {
            throw new UserException(ExceptionType.PASSWORD_INCORRECT.getMessage());
        }
        return userLoginDTO;
    }
}
