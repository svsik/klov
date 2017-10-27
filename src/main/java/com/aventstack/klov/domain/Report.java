package com.aventstack.klov.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Report extends KlovDocument implements Serializable {

    private static final long serialVersionUID = -4343106526681673638L;

    @Id
    private String id;

    private String project;
    private String projectName;
    private String name;
    private Date startTime;
    private Date endTime;
    private Integer duration;
    private List<String> categoryNameList;
    private List<String> categoryIdList;
    
    private Integer parentLength;
    private Integer passParentLength;
    private Integer failParentLength;
    private Integer fatalParentLength;
    private Integer errorParentLength;
    private Integer warningParentLength;
    private Integer skipParentLength;
    private Integer exceptionsParentLength;
    private Integer childLength;
    private Integer passChildLength;
    private Integer failChildLength;
    private Integer fatalChildLength;
    private Integer errorChildLength;
    private Integer warningChildLength;
    private Integer skipChildLength;
    private Integer infoChildLength;
    private Integer debugChildLength;
    private Integer exceptionsChildLength;
    private Integer grandChildLength;
    private Integer passGrandChildLength;
    private Integer failGrandChildLength;   
    private Integer fatalGrandChildLength;
    private Integer errorGrandChildLength;
    private Integer warningGrandChildLength;
    private Integer skipGrandChildLength;
    private Integer infoGrandChildLength;
    private Integer debugGrandChildLength;
    private Integer exceptionsGrandChildLength;
    
    @SuppressWarnings("rawtypes")
    public static Class[] getChildCollections() {
        return new Class[] {
                Test.class,
                Log.class
                /*Author.class,
                Category.class,
                Exception.class,
                Log.class,
                Media.class,
                Environment.class*/
        };
    }
    
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
    
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public Integer getDuration() {
        return duration;
    }
    public void setDuration(Integer duration) {
        this.duration = duration;
    }
    
    public List<String> getCategoryNameList() {
        return categoryNameList;
    }
    public void setCategoryNameList(List<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }

    public List<String> getCategoryIdList() {
        return categoryIdList;
    }
    public void setCategoriesIdList(List<String> categoryIdList) {
        this.categoryIdList = categoryIdList;
    }

    public Integer getParentLength() {
        return parentLength;
    }
    public void setParentLength(Integer parentLength) {
        this.parentLength = parentLength;
    }
    
    public Integer getPassParentLength() {
        return passParentLength;
    }
    public void setPassParentLength(Integer passParentLength) {
        this.passParentLength = passParentLength;
    }
    
    public Integer getFailParentLength() {
        return failParentLength;
    }
    public void setFailParentLength(Integer failParentLength) {
        this.failParentLength = failParentLength;
    }
    
    public Integer getFatalParentLength() {
        return fatalParentLength;
    }
    public void setFatalParentLength(Integer fatalParentLength) {
        this.fatalParentLength = fatalParentLength;
    }
    
    public Integer getErrorParentLength() {
        return errorParentLength;
    }
    public void setErrorParentLength(Integer errorParentLength) {
        this.errorParentLength = errorParentLength;
    }
    
    public Integer getWarningParentLength() {
        return warningParentLength;
    }
    public void setWarningParentLength(Integer warningParentLength) {
        this.warningParentLength = warningParentLength;
    }
    
    public Integer getSkipParentLength() {
        return skipParentLength;
    }
    public void setSkipParentLength(Integer skipParentLength) {
        this.skipParentLength = skipParentLength;
    }
    
    public Integer getExceptionsParentLength() {
        return exceptionsParentLength;
    }
    public void setExceptionsParentLength(Integer exceptionsParentLength) {
        this.exceptionsParentLength = exceptionsParentLength;
    }
    
    public Integer getChildLength() {
        return childLength;
    }
    public void setChildLength(Integer childLength) {
        this.childLength = childLength;
    }
    
    public Integer getPassChildLength() {
        return passChildLength;
    }
    public void setPassChildLength(Integer passChildLength) {
        this.passChildLength = passChildLength;
    }
    
    public Integer getFailChildLength() {
        return failChildLength;
    }
    public void setFailChildLength(Integer failChildLength) {
        this.failChildLength = failChildLength;
    }
    
    public Integer getFatalChildLength() {
        return fatalChildLength;
    }
    public void setFatalChildLength(Integer fatalChildLength) {
        this.fatalChildLength = fatalChildLength;
    }
    
    public Integer getErrorChildLength() {
        return errorChildLength;
    }
    public void setErrorChildLength(Integer errorChildLength) {
        this.errorChildLength = errorChildLength;
    }
    
    public Integer getWarningChildLength() {
        return warningChildLength;
    }
    public void setWarningChildLength(Integer warningChildLength) {
        this.warningChildLength = warningChildLength;
    }
    
    public Integer getSkipChildLength() {
        return skipChildLength;
    }
    public void setSkipChildLength(Integer skipChildLength) {
        this.skipChildLength = skipChildLength;
    }
    
    public Integer getInfoChildLength() {
        return infoChildLength;
    }
    public void setInfoChildLength(Integer infoChildLength) {
        this.infoChildLength = infoChildLength;
    }
    
    public Integer getDebugChildLength() {
        return debugChildLength;
    }
    public void setDebugChildLength(Integer debugChildLength) {
        this.debugChildLength = debugChildLength;
    }
    
    public Integer getExceptionsChildLength() {
        return exceptionsChildLength;
    }
    public void setExceptionsChildLength(Integer exceptionsChildLength) {
        this.exceptionsChildLength = exceptionsChildLength;
    }
    
    public Integer getGrandChildLength() {
        return grandChildLength;
    }
    public void setGrandChildLength(Integer grandChildLength) {
        this.grandChildLength = grandChildLength;
    }
    
    public Integer getPassGrandChildLength() {
        return passGrandChildLength;
    }
    public void setPassGrandChildLength(Integer passGrandChildLength) {
        this.passGrandChildLength = passGrandChildLength;
    }
    
    public Integer getFailGrandChildLength() {
        return failGrandChildLength;
    }
    public void setFailGrandChildLength(Integer failGrandChildLength) {
        this.failGrandChildLength = failGrandChildLength;
    }
    
    public Integer getFatalGrandChildLength() {
        return fatalGrandChildLength;
    }
    public void setFatalGrandChildLength(Integer fatalGrandChildLength) {
        this.fatalGrandChildLength = fatalGrandChildLength;
    }
    
    public Integer getErrorGrandChildLength() {
        return errorGrandChildLength;
    }
    public void setErrorGrandChildLength(Integer errorGrandChildLength) {
        this.errorGrandChildLength = errorGrandChildLength;
    }
    
    public Integer getWarningGrandChildLength() {
        return warningGrandChildLength;
    }
    public void setWarningGrandChildLength(Integer warningGrandChildLength) {
        this.warningGrandChildLength = warningGrandChildLength;
    }
    
    public Integer getSkipGrandChildLength() {
        return skipGrandChildLength;
    }
    public void setSkipGrandChildLength(Integer skipGrandChildLength) {
        this.skipGrandChildLength = skipGrandChildLength;
    }
    
    public Integer getInfoGrandChildLength() {
        return infoGrandChildLength;
    }
    public void setInfoGrandChildLength(Integer infoGrandChildLength) {
        this.infoGrandChildLength = infoGrandChildLength;
    }
    
    public Integer getDebugGrandChildLength() {
        return debugGrandChildLength;
    }
    public void setDebugGrandChildLength(Integer debugGrandChildLength) {
        this.debugGrandChildLength = debugGrandChildLength;
    }
    
    public Integer getExceptionsGrandChildLength() {
        return exceptionsGrandChildLength;
    }
    public void setExceptionsGrandChildLength(Integer exceptionsGrandChildLength) {
        this.exceptionsGrandChildLength = exceptionsGrandChildLength;
    }

    
}
