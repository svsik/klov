package com.aventstack.klov.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Test extends KlovDocument implements Serializable, IncludesMedia {

    private static final long serialVersionUID = -4708786796059060635L;

    @Id
    private String id;
    
    private String parent;
    private String project;
    private String report;
    private Integer level;
    private String name;
    private String status;
    private Boolean bdd;
    private String bddType;
    private Integer childNodesLength;
    private Object duration;
    private Date endTime;
    private Date startTime;
    private String description;
    private Boolean categorized;
    
    private List<Test> nodes;
    private List<Log> logs;
    private List<Media> media;
    private List<String> categoryNameList;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    
    public void setParent(String parent) {
        this.parent = parent;
    }
    public String getParent() {
        return parent;
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
    
    public Integer getLevel() {
        return level;
    }
    public void setLevel(Integer level) {
        this.level = level;
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
    
    public Boolean getBdd() {
        return bdd;
    }
    public void setBdd(Boolean bdd) {
        this.bdd = bdd;
    }
    
    public String getBddType() {
        return bddType;
    }
    public void setBddType(String bddType) {
        this.bddType = bddType;
    }
    
    public Integer getChildNodesLength() {
        return childNodesLength;
    }
    public void setChildNodesLength(Integer childNodesLength) {
        this.childNodesLength = childNodesLength;
    }
    
    public Object getDuration() {
        return duration;
    }
    public void setDuration(Object duration) {
        this.duration = duration;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getCategorized() {
        return categorized;
    }
    public void setCategorized(Boolean categorized) {
        this.categorized = categorized;
    }
    
    public Boolean isLeaf() {
        return childNodesLength == 0;
    }
    
    public void setNodes(List<Test> nodes) {
        this.nodes = nodes;
    }
    public List<Test> getNodes() {
        return nodes;
    }
    
    public void addNode(Test node) {
        if (nodes == null)
            nodes = new ArrayList<Test>();
        
        nodes.add(node);
    }
    
    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
    public List<Log> getLogs() {
        return logs;
    }
    
    public void setMedia(List<Media> media) {
        this.media = media;
    }
    public List<Media> getMedia() {
        return media;
    }

    public List<String> getCategoryNameList() {
        return categoryNameList;
    }
    public void setCategoryNameList(List<String> categoryNameList) {
        this.categoryNameList = categoryNameList;
    }
    
}
