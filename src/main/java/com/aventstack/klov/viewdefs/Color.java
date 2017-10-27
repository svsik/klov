package com.aventstack.klov.viewdefs;

public class Color {

    public Color() { }
    
    public String byStatus(String status) {
        switch (status.trim().toLowerCase()) {
            case "pass" : return "green";
            case "fail" : return "red";
            case "fatal" : return "red";
            case "error" : return "amber";
            case "warning" : return "yellow";
            case "skip" : return "yellow";
            case "info" : return "light-blue";
            case "debug" : return "";
            default:
                return "black";
        }
    }
    
    public String getBootstrapKeyword(String status) {
        switch (status.trim().toLowerCase()) {
            case "pass" : return "success";
            case "fail" : return "danger";
            case "fatal" : return "danger";
            case "error" : return "warning";
            case "warning" : return "warning";
            case "skip" : return "primary";
            case "info" : return "info";
            case "debug" : return "";
            default:
                return "black";
        }
    }
    
}
