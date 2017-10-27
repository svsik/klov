package com.aventstack.klov.domain;

import java.util.Arrays;
import java.util.List;

public enum Status {
    PASS,
    FAIL,
    FATAL,
    ERROR,
    WARNING,
    SKIP,
    DEBUG,
    INFO;
    
    private static List<Status> statusHierarchy = Arrays.asList(
            Status.FATAL,
            Status.FAIL,
            Status.ERROR,
            Status.WARNING,
            Status.SKIP,
            Status.PASS,
            Status.INFO,
            Status.DEBUG
    );
    
    private static List<Status> allowedTestStatusHierarchy = Arrays.asList(
            Status.FATAL,
            Status.FAIL,
            Status.ERROR,
            Status.WARNING,
            Status.SKIP,
            Status.PASS
    );
    
    public static List<Status> getStatusHierarchy() {
        return statusHierarchy;
    }
    
    public static List<Status> getAllowedTestStatusHierarchy() {
        return allowedTestStatusHierarchy;
    }
}
