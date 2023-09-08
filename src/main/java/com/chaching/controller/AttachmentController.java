package com.chaching.controller;

import java.net.HttpURLConnection;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chaching.exception.ErrorDetails;
import com.chaching.model.entity.Attachment;
import com.chaching.model.response.AttachmentResponse;
import com.chaching.service.AttachmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/file")
@Tag(name = "Attachment-Service", description = "Attachment-Service endpoints")
@ApiResponses(value = { @ApiResponse(responseCode = HttpURLConnection.HTTP_NOT_FOUND
                + "", description = "Not Found", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_BAD_REQUEST
                                + "", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorDetails.class))),
                @ApiResponse(responseCode = HttpURLConnection.HTTP_INTERNAL_ERROR
                                + "", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = ErrorDetails.class))) })
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload File", description = "Endpoint to Upload File")
	@ApiResponse(responseCode = HttpURLConnection.HTTP_CREATED + "", description = "CREATED", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = AttachmentResponse.class)) })
    public AttachmentResponse uploadFile(@RequestParam("file") MultipartFile file){

        String downloadURL = "";

        Attachment attachment = attachmentService.saveFile(file);
        downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("/file/download/").path(attachment.getId())
                                            .toUriString();
        
        return new AttachmentResponse(attachment.getFileName(),
                                 downloadURL,
                                 file.getContentType(),
                                 file.getSize());

    }

    @GetMapping("/download/{fileId}")
    @Operation(summary = "Download File", description = "Endpoint to Download File")
    @ApiResponse(responseCode = HttpURLConnection.HTTP_OK + "", description = "OK", content = {
		@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Resource.class)) })
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String fileId){
        Attachment attachment = attachmentService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));

    }

    // while uploading file using postman, Use key -> "file"

    
}
