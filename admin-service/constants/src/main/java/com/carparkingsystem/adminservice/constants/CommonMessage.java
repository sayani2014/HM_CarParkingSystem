package com.carparkingsystem.adminservice.constants;

public enum CommonMessage {
    REGISTRATION_SUCCESSFUL("Registration Successful."),
    LOGIN_SUCCESSFUL("Login Successful.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}