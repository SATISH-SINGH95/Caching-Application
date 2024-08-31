package com.chaching.service;

import javax.servlet.http.HttpServletResponse;

import com.chaching.model.request.UserInfoRequestObject;
import com.chaching.model.response.PageResponse;
import com.chaching.model.response.UserInfoResponse;
import com.chaching.model.response.UserInfoResponseObject;

public interface UserInfoService {

    UserInfoResponseObject createUserInfo(UserInfoRequestObject userInfoRequestObject);

    UserInfoResponse getAllUserInfoByUserStatus(String userStatus);

    PageResponse getAllUserInfo(int pageNo, int pageSize, String sortBy, String sortDir);

    UserInfoResponseObject getSingleUserInfo(Long userInfoId);

    UserInfoResponseObject updateUserInfo(long userInfoId, UserInfoRequestObject userInfoRequestObject);

    void deleteUserInfo(long userInfo);

    void makeUserInactive(Long userInfoId);

    void exportRecordToExcel(HttpServletResponse response) throws Exception;

}
