package com.aventstack.klov.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Log extends KlovDocument implements Serializable, IncludesMedia {

    private static final long serialVersionUID = -7855083118617112442L;

    @Id
    private String id;

    private String project;
    private String report;
    private String test;
    private String testName;
    private Integer sequence;
    private String status;
    private String details;
    private Date timestamp;
    
    private List<Media> media;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public String getProject() {
        return project;
    }
    public void setProject(String project) {
        this.project = project;
    }
    
    public String getReport() {
        return report;
    }
    public void setReport(String report) {
        this.report = report;
    }
    
    public String getTest() {
        return test;
    }
    public void setTest(String test) {
        this.test = test;
    }
    
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    
    public Date getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    public void setMedia(List<Media> media) {
        this.media = media;
    }
    public List<Media> getMedia() {
        return media;
    }
    
}
