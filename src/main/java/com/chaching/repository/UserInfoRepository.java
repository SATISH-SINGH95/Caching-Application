package com.chaching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chaching.model.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long>{

    List<UserInfo> findByUserStatus(String userStatus);
    
}
