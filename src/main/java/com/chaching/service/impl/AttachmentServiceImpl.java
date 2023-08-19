package com.chaching.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.chaching.constants.UserInfoConstants;
import com.chaching.exception.AttachmentNotFoundException;
import com.chaching.exception.InvalidFileNameException;
import com.chaching.model.entity.Attachment;
import com.chaching.repository.AttachmentRepository;
import com.chaching.service.AttachmentService;


@Service
public class AttachmentServiceImpl implements AttachmentService{

    private AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveFile(MultipartFile file) {

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")){
                throw new InvalidFileNameException(HttpStatus.BAD_REQUEST, UserInfoConstants.Message.INVALID_FILE_NAME);
            }
            Attachment attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
            return attachmentRepository.save(attachment);
            
        } catch (Exception e) {
            throw new InvalidFileNameException(HttpStatus.BAD_REQUEST, UserInfoConstants.Message.INVALID_FILE_NAME); 
            
        }
        
    }

    @Override
    public Attachment getAttachment(String fileId) {

        return attachmentRepository.findById(fileId).orElseThrow(
            ()-> new AttachmentNotFoundException(HttpStatus.NOT_FOUND, UserInfoConstants.Message.ATTACHMENT_NOT_FOUND)
        );
        
    }
    
}
