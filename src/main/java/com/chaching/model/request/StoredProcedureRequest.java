package com.chaching.model.request;

import lombok.Data;

@Data
public class StoredProcedureRequest {
    
    private String name;
    private String email;
    private String address;
    private long salary;
    
}
