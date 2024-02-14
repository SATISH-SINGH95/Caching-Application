package com.chaching.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.chaching.constants.UserInfoConstants;
import com.chaching.exception.BadRequestException;
import com.chaching.exception.EntityNotFoundException;
import com.chaching.model.entity.UserInfo;
import com.chaching.model.request.UserInfoRequestObject;
import com.chaching.model.response.PageResponse;
import com.chaching.model.response.UserInfoResponse;
import com.chaching.model.response.UserInfoResponseObject;
import com.chaching.repository.UserInfoRepository;
import com.chaching.service.UserInfoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    private UserInfoRepository userInfoRepository;


    @Override
    public UserInfoResponseObject createUserInfo(UserInfoRequestObject userInfoRequestObject) {
        log.debug("createUserInfo start | usrInfoRequestObject = {}", userInfoRequestObject);
        UserInfoResponseObject responseObject = new UserInfoResponseObject();

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(userInfoRequestObject.getUsername());
        userInfo.setUserEmail(userInfoRequestObject.getUserEmail());
        userInfo.setUserAddress(userInfoRequestObject.getUserAddress());
        userInfo.setUserStatus(UserInfoConstants.USER_STATUS_ACTIVE);
        userInfo.setCreatedDate(LocalDateTime.now());

        UserInfo savedEntity = userInfoRepository.save(userInfo);

        if(savedEntity != null){
            responseObject = savedEntity.getAsObject();
        }

        log.debug("createUserInfo end | UserInfoResponseObject = {}", responseObject);
        return responseObject;
    }

    @Override
    @Cacheable(cacheNames = "users", key = "#userStatus")
    public UserInfoResponse getAllUserInfoByUserStatus(String userStatus) {
        log.debug("getAllUserInfoByUserStatus start | userStatus = {}", userStatus);
        log.info("Get all User Info details based on UserStatus from database");

        UserInfoResponse response = new UserInfoResponse();

        List<UserInfo> userInfoEntities = null;

        if(userStatus == null){
            throw new BadRequestException(HttpStatus.BAD_REQUEST,UserInfoConstants.MESSAGE_INVALID_REQUEST);
        }
        
        userInfoEntities = userInfoRepository.findByUserStatus(userStatus);

        if(userInfoEntities == null || userInfoEntities.isEmpty()){
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.MESSAGE_USER_INFO_NOT_FOUND);
        }

        List<UserInfoResponseObject> listOfRequestObjects = userInfoEntities.stream().map(userEntity -> userEntity.getAsObject()).collect(Collectors.toList());

        response.setUserInfoList(listOfRequestObjects);
        log.debug("getAllUserInfoByUserStatus end | UserInfoResponse = {}", response);
        return response;

    }

    @Override
    public PageResponse getAllUserInfo(int pageNo, int pageSize, String sortBy, String sortDir) {

        log.debug("getAllUserInfo start | pageNo = {}, pageSize = {}, sortBy = {}, sortDir = {}", pageNo, pageSize, sortBy, sortDir);

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<UserInfo> page = userInfoRepository.findAll(pageable);
        List<UserInfo> userInfos = page.getContent();
        
        if(userInfos == null || userInfos.isEmpty()){
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.MESSAGE_USER_INFO_NOT_FOUND);
        }

        List<UserInfoResponseObject> listOfRequestObjects = userInfos.stream().map(userEntity -> userEntity.getAsObject()).collect(Collectors.toList());
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        userInfoResponse.setUserInfoList(listOfRequestObjects);

        PageResponse response = new PageResponse();
        response.setListOfUserInfos(userInfoResponse);
        response.setLast(page.isLast());
        response.setTotalElements(page.getTotalElements());
        response.setTotalPages(page.getTotalPages());
        response.setPageSize(page.getSize());
        response.setPageNo(page.getNumber());
        
        log.debug("getAllUserInfo end | PageResponse = {}", response);
        return response;

    }

    @Override
    @Cacheable(cacheNames = "users", key = "#userInfoId")
    public UserInfoResponseObject getSingleUserInfo(Long userInfoId) {
        log.debug("getSingleUserInfo start | userInfoId = {}", userInfoId);
        log.info("Get Single User from Database");
        UserInfoResponseObject userInfoResponseObject = null;

        UserInfo userInfo = userInfoRepository.findById(userInfoId).orElseThrow(
            () -> new EntityNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.MESSAGE_USER_INFO_ID_NOT_FOUND));

        if(userInfo != null){
            userInfoResponseObject = userInfo.getAsObject();
        }

        log.debug("getSingleUserInfo end | UserInfoResponseObject = {}", userInfoResponseObject);
        return userInfoResponseObject;
    }

    @Override
    @CachePut(cacheNames = "users", key = "#userInfoId")
    public UserInfoResponseObject updateUserInfo(long userInfoId, UserInfoRequestObject userInfoRequestObject) {
        log.debug("updateUserInfo start | userInfoId = {}, UserInfoRequestObject = {}", userInfoId, userInfoRequestObject);

        UserInfoResponseObject userInfoResponseObject = null;

        UserInfo user = userInfoRepository.findById(userInfoId).orElseThrow(
            () -> new EntityNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.MESSAGE_USER_INFO_ID_NOT_FOUND));
        
        user.setUpdateDate(LocalDateTime.now());
        user.setUserAddress(userInfoRequestObject.getUserAddress());

        UserInfo updatedEntity = userInfoRepository.save(user);
        if(updatedEntity != null){
            userInfoResponseObject = updatedEntity.getAsObject();
        }

        log.debug("updateUserInfo end | UserInfoResponseObject = {}", userInfoResponseObject);
        return userInfoResponseObject;
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#userInfoId")
    public void deleteUserInfo(long userInfoId) {
        log.debug("deleteUserInfo start | userInfoId = {}", userInfoId);

        UserInfo userInfo = userInfoRepository.findById(userInfoId).orElseThrow(
            () -> new EntityNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.MESSAGE_USER_INFO_ID_NOT_FOUND));
        userInfoRepository.delete(userInfo);

        log.debug("deleteUserInfo end ", "User Deleted Successfully");

        
    }

    @Override
    public void makeUserInactive(Long userInfoId) {
        log.debug("makeUserInactive start | userStatus {}", "User is Active !");

        UserInfo userInfo = userInfoRepository.findById(userInfoId).orElseThrow(
            () -> new EntityNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.MESSAGE_USER_INFO_ID_NOT_FOUND));

        userInfo.setUserStatus(UserInfoConstants.USER_STATUS_INACTIVE);
        userInfo.setUpdateDate(LocalDateTime.now());
        userInfoRepository.save(userInfo);
        log.debug("makeUserInactive end | userStatus {}", "User is Inactive now !");
       
    }
    
}
