package com.aventstack.klov.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Category extends KlovDocument implements Serializable {

    private static final long serialVersionUID = 7401623515165778762L;

    @Id
    private String id;
    
    private String tests;
    private String project;
    private String report;
    private String name;
    private String status;
    private String testName;  
    private Long timeTaken;
    private Integer testLength;
    private List<String> testNameList;
    private List<String> testIdList;
    
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
    
    public Long getTimeTaken() {
        return timeTaken;
    }
    public void setTimeTaken(Long timeTaken) {
        this.timeTaken = timeTaken;
    }

    public Integer getTestLength() {
        return testLength;
    }
    public void setTestLength(Integer testLength) {
        this.testLength = testLength;
    }
    
    public List<String> getTestNameList() {
        return testNameList;
    }
    public void setTestNameList(List<String> testNameList) {
        this.testNameList = testNameList;
    }

    public List<String> getTestIdList() {
        return testIdList;
    }
    public void setTestIdList(List<String> testIdList) {
        this.testIdList = testIdList;
    }
    
}
