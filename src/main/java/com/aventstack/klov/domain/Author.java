package com.aventstack.klov.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Author extends KlovDocument implements Serializable {

    private static final long serialVersionUID = 7401623515165778762L;

    @Id
    private String id;
    
    private String tests;
    private String project;
    private String report;
    private String name;
    private String status;
    private String testName;  
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public void setTests(String tests) {
        this.tests = tests;
    }
    public String getTests() {
        return tests;
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
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
}
