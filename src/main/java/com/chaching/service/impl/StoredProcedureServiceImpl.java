package com.chaching.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaching.model.entity.StoredProcedureEntity;
import com.chaching.model.request.StoredProcedureRequest;
import com.chaching.model.response.StoredProcedureResponse;
import com.chaching.repository.StoredProcedureRepository;
import com.chaching.service.StoredProcedureService;

@Service
@Transactional
public class StoredProcedureServiceImpl implements StoredProcedureService{

    @Autowired
    private StoredProcedureRepository storedProcedureRepository;

    @Override
    public Long getSalaryById(Long id) {
        return storedProcedureRepository.getSalaryById(id);
    }

    @Override
    public StoredProcedureResponse createStoredProcedureData(StoredProcedureRequest storedProcedureRequest) {
        StoredProcedureEntity entity = new StoredProcedureEntity();
        entity.setName(storedProcedureRequest.getName());
        entity.setAddress(storedProcedureRequest.getAddress());
        entity.setEmail(storedProcedureRequest.getEmail());
        entity.setSalary(storedProcedureRequest.getSalary());
        StoredProcedureEntity savedEntity = storedProcedureRepository.save(entity);

        return savedEntity.getAsResponse();
    }

    @Override
    public Long getTotalSalaryOfStoredProcTable() {
        return storedProcedureRepository.getTotalSalayOfTable();
    }

    @Override
    public List<StoredProcedureResponse> getTotalStoreProList() {
        List<StoredProcedureEntity> entityList = storedProcedureRepository.getAllStoredProcList();
        List<StoredProcedureResponse> response = entityList.stream().map(StoredProcedureEntity :: getAsResponse).collect(Collectors.toList());
        return response;
    }

    
    
}
