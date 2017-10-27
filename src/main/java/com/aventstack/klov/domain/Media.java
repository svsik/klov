package com.aventstack.klov.domain;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Media extends KlovDocument implements Serializable {

    private static final long serialVersionUID = -4708786796059060635L;

    @Id
    private String id;
    
    private ObjectId test;
    private ObjectId log;
    private ObjectId project;
    private ObjectId report;
    private Integer sequence;
    private String testName;
    private String path;
    private Date updatedAt;
    private String mediaType;
    private String ext;
    private String base64String;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public ObjectId getTest() {
        return test;
    }
    public void setTest(ObjectId test) {
        this.test = test;
    }
    
    public ObjectId getLog() {
        return log;
    }
    public void setLog(ObjectId log) {
        this.log = log;
    }
    
    public ObjectId getProject() {
        return project;
    }
    public void setProject(ObjectId project) {
        this.project = project;
    }
    
    public ObjectId getReport() {
        return report;
    }
    public void setReport(ObjectId report) {
        this.report = report;
    }
    
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    public String getTestName() {
        return testName;
    }
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getMediaType() {
        return mediaType;
    }
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
    
    public String getFileExtension() {
        return ext;
    }
    public void setFileExtension(String ext) {
        this.ext = ext;
    }
    
    public String getBase64String() {
        return base64String;
    }
    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }
        
}
