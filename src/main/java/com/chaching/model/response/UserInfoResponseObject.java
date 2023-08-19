package com.chaching.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserInfoResponseObject implements Serializable{

    private long userInfoId;
    private String username;
    private String userAddress;
    private String userEmail;
    private String userStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    
}
