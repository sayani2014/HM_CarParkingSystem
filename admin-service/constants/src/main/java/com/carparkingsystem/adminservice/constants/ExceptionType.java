package com.carparkingsystem.adminservice.constants;

public enum ExceptionType {
    USER_ALREADY_EXIST("User Already Exist."),
    ID_NOT_FOUND("Unable to get sequence id for key : ");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

