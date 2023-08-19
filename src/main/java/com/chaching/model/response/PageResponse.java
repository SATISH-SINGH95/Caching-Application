package com.chaching.model.response;

import lombok.Data;

@Data
public class PageResponse {

    private UserInfoResponse listOfUserInfos;
    private long totalElements;
    private long totalPages;
    private long pageNo;
    private long pageSize;
    private boolean isLast;

    
}
