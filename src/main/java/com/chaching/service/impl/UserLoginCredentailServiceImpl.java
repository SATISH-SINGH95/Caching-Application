package com.chaching.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chaching.constants.UserInfoConstants;
import com.chaching.exception.BadRequestException;
import com.chaching.model.entity.UserLoginCredentials;
import com.chaching.model.request.UserLoginCredentialRequest;
import com.chaching.repository.UserLoginCredentialRepository;
import com.chaching.service.UserLoginCredentailService;


@Service
public class UserLoginCredentailServiceImpl implements UserLoginCredentailService{

    @Autowired
    private UserLoginCredentialRepository userLoginCredentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserLoginCredentialRequest userLoginCredentialRequest) {

        if(userLoginCredentialRepository.existsByUserName(userLoginCredentialRequest.getUserName())){
            throw new BadRequestException(HttpStatus.BAD_REQUEST, UserInfoConstants.Message.MESSAGE_USER_ALREADY_EXIST);
        }

        UserLoginCredentials userLoginCredentials = new UserLoginCredentials();
        userLoginCredentials.setFirstName(userLoginCredentialRequest.getFirstName());
        userLoginCredentials.setLastName(userLoginCredentialRequest.getLastName());
        userLoginCredentials.setStatus(userLoginCredentialRequest.getStatus());
        userLoginCredentials.setRole(userLoginCredentialRequest.getRole());
        userLoginCredentials.setUserName(userLoginCredentialRequest.getUserName());
        userLoginCredentials.setPassword(passwordEncoder.encode(userLoginCredentialRequest.getPassword()));
        userLoginCredentials.setEmail(userLoginCredentialRequest.getEmail());
        userLoginCredentials.setCreatedDate(LocalDateTime.now());

        userLoginCredentialRepository.save(userLoginCredentials);

    }
    
}
