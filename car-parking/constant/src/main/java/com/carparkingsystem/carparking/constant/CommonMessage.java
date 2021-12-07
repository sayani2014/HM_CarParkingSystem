package com.carparkingsystem.carparking.constant;

public enum CommonMessage {
    REGISTRATION_SUCCESSFUL("Registration Successful."),
    FETCHED_DETAILS_SUCCESSFULLY("Fetched Parking Details Successfully.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
