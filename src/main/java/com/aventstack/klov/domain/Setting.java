package com.aventstack.klov.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Setting extends KlovDocument implements Serializable {

    private static final long serialVersionUID = 7401623515165778762L;

    @Id
    private String id;
    
    private String name;
    private String value; 
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    
}
