package com.chaching.controller;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaching.constants.UserInfoConstants;
import com.chaching.model.request.UserLoginCredentialRequest;
import com.chaching.service.UserLoginCredentailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/user")
public class UserLoginCredentailController {

    @Autowired
    private UserLoginCredentailService userLoginCredentailService;

    @PostMapping("/create")
	@Operation(summary = "Create User-Crential", description = "Endpoint to create User-Crential")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_CREATED + "", description = "CREATED", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) })
    public ResponseEntity<String> createUser(
        @Parameter(name = "userLoginCredentialRequest", description = "userLoginCredentialRequest is required", required = true, schema = @Schema(implementation = UserLoginCredentialRequest.class)) @RequestBody @Valid UserLoginCredentialRequest userLoginCredentialRequest)
    {
        userLoginCredentailService.createUser(userLoginCredentialRequest);
        return new ResponseEntity<String>(UserInfoConstants.Message.MESSAGE_USER_REGISTERED_SUCCESSFULLY, HttpStatus.CREATED);
    }
    
}
