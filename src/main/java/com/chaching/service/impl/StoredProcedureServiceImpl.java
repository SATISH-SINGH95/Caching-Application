package com.chaching.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.chaching.constants.EmployeeConstants;
import com.chaching.exception.BadRequestException;
import com.chaching.exception.EntityNotFoundException;
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

        if(entityList.isEmpty() || entityList == null){
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, EmployeeConstants.MESSAGE_NO_DATA_FOUND);
        }

        List<StoredProcedureResponse> response = entityList.stream().map(StoredProcedureEntity :: getAsResponse).collect(Collectors.toList());
        return response;
    }

    @Override
    public List<StoredProcedureResponse> getListOfEmployeesByAddress(String address) {
        if(address == null){
            throw new BadRequestException(HttpStatus.BAD_REQUEST, EmployeeConstants.MESSAGE_INVALID_REQUEST);
        }

        List<StoredProcedureEntity> listOfEmpEntity = storedProcedureRepository.getListOfEmployeesByAddress(address);
        
        if(listOfEmpEntity.isEmpty() || listOfEmpEntity == null){
            throw new EntityNotFoundException(HttpStatus.NOT_FOUND, EmployeeConstants.MESSAGE_NO_DATA_FOUND);
        }
        
        List<StoredProcedureResponse> response = listOfEmpEntity.stream().map(StoredProcedureEntity :: getAsResponse).collect(Collectors.toList());
    
        return response;
    
    }

    
    
}
