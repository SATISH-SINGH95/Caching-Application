package com.chaching.model.request;

import lombok.Data;

@Data
public class UserLoginCredentialRequest {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;
    private Boolean status;
    private String role;

}
