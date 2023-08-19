package com.chaching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chaching.model.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String>{
    
}
