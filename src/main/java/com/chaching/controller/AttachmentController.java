package com.chaching.controller;

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

import com.chaching.model.entity.Attachment;
import com.chaching.model.response.AttachmentResponse;
import com.chaching.service.AttachmentService;

@RestController
@RequestMapping("/file")
public class AttachmentController {

    private AttachmentService attachmentService;

    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @PostMapping("/upload")
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
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") String fileId){
        Attachment attachment = attachmentService.getAttachment(fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=\"" + attachment.getFileName() + "\"")
                .body(new ByteArrayResource(attachment.getData()));

    }

    
}
