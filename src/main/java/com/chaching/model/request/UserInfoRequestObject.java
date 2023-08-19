package com.chaching.model.request;

import javax.validation.constraints.NotBlank;

import com.chaching.annotation.EmailAnnotation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserInfoRequestObject {

    @Schema(description = "userName", example = "abcd")
    @NotBlank(message = "User Name can not be null")
    private String username;

    @Schema(description = "userAddress", example = "abcd")
    @NotBlank(message = "User Address can not be null")
    private String userAddress;

    @Schema(description = "userEmail", example = "abcd")
    @NotBlank(message = "User Email can not be null")
    @EmailAnnotation
    private String userEmail;

    
}
