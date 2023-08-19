package com.chaching.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponse {

    private String filelName;
    private String downloadURL;
    private String fileType;
    private Long fileSize;
    
}
