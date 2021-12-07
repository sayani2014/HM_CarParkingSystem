package com.carparkingsystem.carparking.constant;

public enum ExceptionType {
    UNAUTHORISED_SPACE("Space Name is invalid."),
    UNAVAILABLE_SLOT("Slot not available.");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
