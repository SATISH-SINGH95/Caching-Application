package com.chaching.controller;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaching.exception.ErrorDetails;
import com.chaching.model.request.UserInfoRequestObject;
import com.chaching.model.response.PageResponse;
import com.chaching.model.response.UserInfoResponse;
import com.chaching.model.response.UserInfoResponseObject;
import com.chaching.service.UserInfoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/userInfo")
@Tag(name = "EmployeeApp", description = "Employee endpoints")
@ApiResponses(value = { @ApiResponse(responseCode = HttpURLConnection.HTTP_NOT_FOUND
                + "", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST
                                + "", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                                + "", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/create")
	@Operation(summary = "Create UserInfo details", description = "Endpoint to create UserInfo Details")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_CREATED + "", description = "CREATED", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserInfoRequestObject.class)) })
	public ResponseEntity<UserInfoResponseObject> createEmployee(
		@Parameter(name = "UserInfoRequestObject", description = "UserInfoRequestObject is required", required = true, schema = @Schema(implementation = UserInfoRequestObject.class)) @RequestBody @Valid UserInfoRequestObject userInfoRequestObject)
	{
		log.debug("createEmployee start | userInfoRequestObject = {}", userInfoRequestObject);
		UserInfoResponseObject response = userInfoService.createUserInfo(userInfoRequestObject);
		log.debug("createEmployee end | UserInfoResponseObject = {}", response);
		return new ResponseEntity<UserInfoResponseObject>(response, HttpStatus.CREATED);
	}

    @GetMapping("/id/{userInfoId}")
	@Operation(summary = "Get single userInfo detail", description = "Endpoint to Get single userInfo Detail")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class)) })
	public ResponseEntity<UserInfoResponseObject> getSingleEmployee(
		@Parameter(name = "userInfoId", description = "userInfoId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable final Long userInfoId)
		{
		log.debug("getSingleEmployee start | userInfoId = {}", userInfoId);
		UserInfoResponseObject response = userInfoService.getSingleUserInfo(userInfoId);
		log.debug("getSingleEmployee end | UserInfoResponseObject = {}", response);
		return new ResponseEntity<UserInfoResponseObject>(response, HttpStatus.OK);
	}

	@GetMapping("/user/userStatus/{userStatus}")
	@Operation(summary = "Get all userInfo detail based on userStatus", description = "Endpoint to Get userInfo detail based on userStatus")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = String.class)) })
	public ResponseEntity<UserInfoResponse> getAllUserInfoBasedOnUserStatus(
		@Parameter(name = "userStatus", description = "userStatus is required", required = true, schema = @Schema(implementation = String.class)) @PathVariable final String userStatus
	){
		log.debug("getAllUserInfoBasedOnUserStatus start | userStatus = {}", userStatus);
		UserInfoResponse responses = userInfoService.getAllUserInfoByUserStatus(userStatus);
		log.debug("getAllUserInfoBasedOnUserStatus end | UserInfoResponse = {}", responses);
		return new ResponseEntity<UserInfoResponse>(responses, HttpStatus.OK);
	}

	// http://localhost:9191/userInfo/
	// http://localhost:9191/userInfo/?pageNo=0&pageSize=5&sortBy=id&sortDir=asc

	@GetMapping("/")
	@Operation(summary = "Get all userInfo details", description = "Endpoint to Get userInfo details")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)) })
	public ResponseEntity<PageResponse> getAllUserInfos(
		@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
		@RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
		@RequestParam(value = "sortBy", defaultValue = "userInfoId", required = false) String sortBy,
		@RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir
	){
		log.debug("getAllUserInfos start | pageNo = {}, pageSize = {}, sortBy = {}, sortDir = {}", pageNo, pageSize, sortBy, sortDir);
		PageResponse responses = userInfoService.getAllUserInfo(pageNo, pageSize, sortBy, sortDir);
		log.debug("getAllUserInfos end | PageResponse = {}", responses);
		return new ResponseEntity<PageResponse>(responses, HttpStatus.OK);
	}

	@PutMapping("/id/{userInfoId}")
	@Operation(summary = "Update User Info", description = "Endpoint to Update User Info")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)) })
	public ResponseEntity<UserInfoResponseObject> updateUSerInfo(
		@Parameter(name = "userInfoId", description = "userInfoId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable final Long userInfoId,
		@Parameter(name = "UserInfoRequestObject", description = "UserInfoRequestObject is required", required = true, schema = @Schema(implementation = UserInfoRequestObject.class)) @RequestBody @Valid UserInfoRequestObject userInfoRequestObject)
		{
		log.debug("updateUSerInfo start | userInfoRequestObject = {}, userInfoId = {}", userInfoRequestObject, userInfoId);
		UserInfoResponseObject response = userInfoService.updateUserInfo(userInfoId, userInfoRequestObject);
		log.debug("updateUSerInfo end | UserInfoResponseObject = {}", response);
		return new ResponseEntity<UserInfoResponseObject>(response, HttpStatus.OK);
	}

	@PatchMapping("/id/{userInfoId}")
	@Operation(summary = "Make user Inactive", description = "Endpoint to Make user Inactive")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Object.class)) })
	public ResponseEntity<String> markUserAsInactive(
		@Parameter(name = "userInfoId", description = "userInfoId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable final Long userInfoId)
		{
		log.debug("markUserAsInactive start | userInfoId = {}", userInfoId);
		userInfoService.makeUserInactive(userInfoId);
		log.debug("markUserAsInactive end | message = {}", "User in Inactive now !");
		return new ResponseEntity<String>("User is Inactive now !", HttpStatus.OK);
	}

	@DeleteMapping("/id/{userInfoId}")
	@Operation(summary = "Delete userInfo detail", description = "Endpoint to Delete userInfo detail")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class)) })
	public ResponseEntity<String> deleteUserInfoDetail(
		@Parameter(name = "userInfoId", description = "userInfoId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable final Long userInfoId)
		{
		log.debug("deleteUserInfoDetail start | userInfoId = {}", userInfoId);
		userInfoService.deleteUserInfo(userInfoId);
		log.debug("deleteUserInfoDetail end" , "User deleted successfully");
		return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
	}


    
}