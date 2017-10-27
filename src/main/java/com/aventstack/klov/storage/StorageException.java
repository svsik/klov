package com.aventstack.klov.storage;

public class StorageException extends RuntimeException {

    private static final long serialVersionUID = -6692215846325891385L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
    
}