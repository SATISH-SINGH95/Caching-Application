package com.chaching.service;

import org.springframework.web.multipart.MultipartFile;

import com.chaching.model.entity.Attachment;

public interface AttachmentService {

    Attachment saveFile(MultipartFile file);

    Attachment getAttachment(String fileId);
    
}
