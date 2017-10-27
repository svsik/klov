package com.aventstack.klov.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Exception extends KlovDocument implements Serializable {

    private static final long serialVersionUID = 7401623515165778762L;

    @Id
    private String id;
    
    private String project;
    private String report;
    private String name;
    private String stacktrace;
    private Integer testCount;  
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public void setProject(String project) {
        this.project = project;
    }
    public String getProject() {
        return project;
    }
    
    public void setReport(String report) {
        this.report = report;
    }
    public String getReport() {
        return report;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getStacktrace() {
        return stacktrace;
    }
    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }

    public Integer getTestCount() {
        return testCount;
    }
    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }
    
}
