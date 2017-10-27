package com.aventstack.klov.domain;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class UserSafe {

    private String name;
    private Boolean admin;
    private Date lastSignOn;
    private Date createdAt;
    private Date updatedAt;

    public UserSafe() { }
    
    public UserSafe(User user) {    
        name = user.getName();
        admin = user.getAdmin();
        lastSignOn = user.getLastSignOn();
        createdAt = user.getCreatedAt();
        updatedAt = user.getUpdatedAt();
    }
    
    public String getName() {
        return name;
    }

    public Boolean getAdmin() {
        return admin;
    }
    
    public Date getLastSignOn() {
        return lastSignOn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
    
    public Date getUpdatedAt() {
        return updatedAt;
    }
    
}
