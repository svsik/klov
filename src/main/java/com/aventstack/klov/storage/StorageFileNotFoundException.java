package com.aventstack.klov.storage;

public class StorageFileNotFoundException extends StorageException {

    private static final long serialVersionUID = -4039039775405589365L;

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}