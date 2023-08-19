package com.chaching.model.response;

import java.util.List;

import lombok.Data;

@Data
public class UserInfoResponse {

    private List<UserInfoResponseObject> userInfoList;
    
}
