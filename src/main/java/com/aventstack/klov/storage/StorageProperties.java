package com.aventstack.klov.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("application")
public class StorageProperties {
    
    @Value( "${file.storage.location}" )
    private String storageLocation = "uploads";

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String location) {
        this.storageLocation = location;
    }
    
}
