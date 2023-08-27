package com.chaching.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaching.model.entity.UserLoginCredentials;

public interface UserLoginCredentialRepository extends JpaRepository<UserLoginCredentials, Long>{

    UserLoginCredentials findByUserName(String userName);

    Boolean existsByUserName(String userName);
    
}
