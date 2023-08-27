package com.chaching.springService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chaching.constants.UserInfoConstants;
import com.chaching.exception.UserNotFoundException;
import com.chaching.model.entity.UserLoginCredentials;
import com.chaching.repository.UserLoginCredentialRepository;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserLoginCredentialRepository userLoginCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLoginCredentials user = userLoginCredentialRepository.findByUserName(username);

        if(user != null){
            return new CustomUserDetail(user);
        }
        else{
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.Message.MESSAGE_USER_NOT_FOUND);
        }

    }


    
}
