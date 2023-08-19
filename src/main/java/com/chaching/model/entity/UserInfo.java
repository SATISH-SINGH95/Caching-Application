package com.chaching.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chaching.model.response.UserInfoResponseObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_INFO")
public class UserInfo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_INFO_ID")
    private long userInfoId;

    @Column(name = "NAME")
    private String username;

    @Column(name = "ADDRESS")
    private String userAddress;

    @Column(name = "EMAIL")
    private String userEmail;

    @Column(name = "USER_STATUS")
    private String userStatus;

    // @Column(name = "PASSWORD")
    // private String password;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updateDate;

    public UserInfoResponseObject getAsObject(){

        UserInfoResponseObject responseObject = new UserInfoResponseObject();
        responseObject.setUserInfoId(this.userInfoId);
        responseObject.setUsername(this.username);
        responseObject.setUserAddress(this.userAddress);
        responseObject.setUserEmail(this.userEmail);
        responseObject.setUserStatus(this.userStatus);
        responseObject.setCreatedDate(this.createdDate);
        responseObject.setUpdateDate(this.updateDate);
        return responseObject;
    }


    
}
