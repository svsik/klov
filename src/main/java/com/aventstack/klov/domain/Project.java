package com.aventstack.klov.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Project extends KlovDocument implements Serializable {

    private static final long serialVersionUID = 3347279891161918377L;

    @Id
    private String id;
    private String name;
    
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
    
}
