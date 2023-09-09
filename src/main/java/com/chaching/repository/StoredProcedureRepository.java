package com.chaching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

import com.chaching.model.entity.StoredProcedureEntity;

public interface StoredProcedureRepository extends JpaRepository<StoredProcedureEntity, Long>{

    @Procedure("getSalaryOfStoreProcById")
    public Long getSalaryById(Long id);


    @Procedure("getTotalSalayOfTable")
    public Long getTotalSalayOfTable();


    @Procedure("getTotalStoreProList")
    public List<StoredProcedureEntity> getAllStoredProcList();
    
}
