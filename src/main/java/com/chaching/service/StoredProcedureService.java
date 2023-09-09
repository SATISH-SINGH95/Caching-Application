package com.chaching.service;

import java.util.List;

import com.chaching.model.request.StoredProcedureRequest;
import com.chaching.model.response.StoredProcedureResponse;

public interface StoredProcedureService {

    StoredProcedureResponse createStoredProcedureData(StoredProcedureRequest storedProcedureRequest);

    Long getSalaryById(Long id);

    Long getTotalSalaryOfStoredProcTable();

    List<StoredProcedureResponse> getTotalStoreProList();

}
