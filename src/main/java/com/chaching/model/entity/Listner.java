package com.chaching.model.entity;

import javax.persistence.PrePersist;

public class Listner {

    @PrePersist
    public void ListnerCallBackMethod(Object object){
        System.out.println("Listner call back method is called !");
    }

}
