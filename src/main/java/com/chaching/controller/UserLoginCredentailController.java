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
import com.chaching.exception.ErrorDetails;
import com.chaching.model.request.UserLoginCredentialRequest;
import com.chaching.service.UserLoginCredentailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/user")
@Tag(name = "Login-Credential", description = "Login-Credential endpoints")
@ApiResponses(value = { @ApiResponse(responseCode = HttpURLConnection.HTTP_NOT_FOUND
                + "", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST
                                + "", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                                + "", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
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
