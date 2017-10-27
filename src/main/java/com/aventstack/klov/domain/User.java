package com.aventstack.klov.domain;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    @Id
    private String id;
    
    private String name;
    private String password;
    private Boolean admin;
    private Date lastSignOn;
    private Date createdAt;
    private Date updatedAt;
    private String firstName;
    private String lastName;
    private String url;
    private String company;
    private String location;
    private String email;
    
    public User(String name, String password, Boolean admin) {
        this.name = name;
        this.password = password;
        this.admin = admin;
        createdAt = DateTime.now().toDate();
        updatedAt = DateTime.now().toDate();
    }
    
    public User() { }
    
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
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public Boolean getAdmin() {
        return admin;
    }
    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
    
    public Date getLastSignOn() {
        return lastSignOn;
    }
    public void setLastSignOn(Date lastSignOn) {
        this.lastSignOn = lastSignOn;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
