package com.chaching.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chaching.model.response.StoredProcedureResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "STORED_PROCEDURE", schema = "USERINFO")
@NoArgsConstructor
@AllArgsConstructor
public class StoredProcedureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "SALARY")
    private long salary;

    public StoredProcedureResponse getAsResponse(){
        
        StoredProcedureResponse response = new StoredProcedureResponse();
        response.setName(this.name);
        response.setEmail(this.email);
        response.setAddress(this.address);
        response.setSalary(this.salary);
        return response;

    }

}
