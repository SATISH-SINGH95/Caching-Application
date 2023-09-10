package com.chaching.controller;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chaching.exception.ErrorDetails;
import com.chaching.model.request.StoredProcedureRequest;
import com.chaching.model.response.StoredProcedureResponse;
import com.chaching.service.StoredProcedureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/storedProcedure")
@Tag(name = "Stored-Procedure", description = "Stored-Procedure endpoints")
@ApiResponses(value = { @ApiResponse(responseCode = HttpURLConnection.HTTP_NOT_FOUND
                + "", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST
                                + "", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                                + "", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
public class StoredProcedureController {

    @Autowired
    private StoredProcedureService storedProcedureService;

    @GetMapping("/id/{id}")
    public ResponseEntity<Long> getSalaryById(@PathVariable Long id){
        Long salaryById = storedProcedureService.getSalaryById(id);
        return new ResponseEntity<Long>(salaryById, HttpStatus.OK);
    }

    @PostMapping("/create")
    @Operation(summary = "Create Stored Procedure Data", description = "Endpoint to Create Stored Procedure Data")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_CREATED + "", description = "CREATED", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = StoredProcedureResponse.class)) })
    public ResponseEntity<StoredProcedureResponse> createStoredProcedureData(
        @Parameter(name = "storedProcedureRequest", description = "storedProcedureRequest is required", required = true, schema = @Schema(implementation = StoredProcedureRequest.class)) @RequestBody StoredProcedureRequest request)
    {
        StoredProcedureResponse response = storedProcedureService.createStoredProcedureData(request);
        return new ResponseEntity<StoredProcedureResponse>(response, HttpStatus.CREATED);
    }

    @GetMapping("/totalSalaryOfTable")
    @Operation(summary = "Get Total Salary Of Store Procedure Table", description = "Endpoint to Get Total Salary Of Store Procedure Table")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Long.class)) })
    public ResponseEntity<Long> getTotalSalaryOfStoreProcTable(){
        Long totalSalary = storedProcedureService.getTotalSalaryOfStoredProcTable();
        return new ResponseEntity<Long>(totalSalary, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<StoredProcedureResponse>> getTotalStoreProList(){
        List<StoredProcedureResponse> response = storedProcedureService.getTotalStoreProList();
        return new ResponseEntity<List<StoredProcedureResponse>>(response, HttpStatus.OK);
    }

    @GetMapping("/getListOfEmployeesByAddress/{address}")
    @Operation(summary = "Get list of employees by Address", description = "Endpoint to Get list of employees by Address")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArrayList.class)) })
    public ResponseEntity<List<StoredProcedureResponse>> getListOfEmployeesByAddress(
        @Parameter(name = "address", description = "address is required", required = true, schema = @Schema(implementation = String.class)) @PathVariable final String address)
    {
        List<StoredProcedureResponse> response = storedProcedureService.getListOfEmployeesByAddress(address);
        return new ResponseEntity<List<StoredProcedureResponse>>(response, HttpStatus.OK);
    }




    
}
