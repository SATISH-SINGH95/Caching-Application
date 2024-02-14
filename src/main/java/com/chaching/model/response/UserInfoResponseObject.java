package com.chaching.model.response;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoResponseObject implements Serializable{

    private long userInfoId;
    private String username;
    private String userAddress;
    private String userEmail;
    private String userStatus;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    
}

// @JsonInclude(JsonInclude.Include.NON_NULL)
// @JsonIgnoreProperties(ignoreUnknown = true)

// These two properties is basically used to avoid those field which is null. So that in the response you will get only
// those field which are non null
