package com.chaching.controller;

import java.net.HttpURLConnection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaching.exception.ErrorDetails;
import com.chaching.model.request.EmployeeRequestObject;
import com.chaching.model.response.EmployeeResponse;
import com.chaching.model.response.EmployeeResponseObject;
import com.chaching.service.EmployeeService;

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
@RequestMapping("/employees")
@Tag(name = "Employee-App", description = "Employee endpoints")
@ApiResponses(value = { @ApiResponse(responseCode = HttpURLConnection.HTTP_NOT_FOUND
                + "", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST
                                + "", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                                + "", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/create")
	@Operation(summary = "Create Employee details", description = "Endpoint to create Employee Details")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_CREATED + "", description = "CREATED", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeResponseObject.class)) })
	public ResponseEntity<EmployeeResponseObject> createEmployee(
		@Parameter(name = "EmployeeRequestObject", description = "UserInfoRequestObject is required", required = true, schema = @Schema(implementation = EmployeeRequestObject.class)) @RequestBody @Valid EmployeeRequestObject employeeRequestObject)
	{
		log.debug("createEmployee start | userInfoRequestObject = {}", employeeRequestObject);
		EmployeeResponseObject response = employeeService.createEmployee(employeeRequestObject);
		log.debug("createEmployee end | EmployeeResponseObject = {}", response);
		return new ResponseEntity<EmployeeResponseObject>(response, HttpStatus.CREATED);
	}


    @GetMapping("/id/{employeeId}")
	@Operation(summary = "Get single employee detail", description = "Endpoint to Get single employee Detail")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeResponseObject.class)) })
	public ResponseEntity<EmployeeResponseObject> getSingleEmployee(
		@Parameter(name = "employeeId", description = "employeeId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable final Long employeeId)
		{
		log.debug("getSingleEmployee start | userInfoId = {}", employeeId);
		EmployeeResponseObject response = employeeService.getSingleEmployee(employeeId);
		log.debug("getSingleEmployee end | EmployeeResponseObject = {}", response);
		return new ResponseEntity<EmployeeResponseObject>(response, HttpStatus.OK);
	}

    @GetMapping("/")
	@Operation(summary = "Get all employee details", description = "Endpoint to Get all employee details")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeResponse.class)) })
	public ResponseEntity<EmployeeResponse> getAllUserInfos()
    {
		log.debug("getAllUserInfos start");
		EmployeeResponse responses = employeeService.getAllEmployee();
		log.debug("getAllEmployees end | responses = {}", responses);
		return new ResponseEntity<EmployeeResponse>(responses, HttpStatus.OK);
	}

    @PutMapping("/id/{employeeId}")
	@Operation(summary = "Update Employee Info", description = "Endpoint to Update Employee Info")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = EmployeeResponseObject.class)) })
	public ResponseEntity<EmployeeResponseObject> updateEmployee(
		@Parameter(name = "employeeId", description = "employeeId is required", required = true, schema = @Schema(implementation = Long.class)) @PathVariable final Long employeeId,
		@Parameter(name = "EmployeeRequestObject", description = "EmployeeRequestObject is required", required = true, schema = @Schema(implementation = EmployeeRequestObject.class)) @RequestBody @Valid EmployeeRequestObject employeeRequestObject)
		{
		log.debug("updateEmployee start | employeeRequestObject = {}, employeeId = {}", employeeRequestObject, employeeId);
		EmployeeResponseObject response = employeeService.updateEmployee(employeeId, employeeRequestObject);
		log.debug("updateEmployee end | response = {}", response);
		return new ResponseEntity<EmployeeResponseObject>(response, HttpStatus.OK);
	}

}
