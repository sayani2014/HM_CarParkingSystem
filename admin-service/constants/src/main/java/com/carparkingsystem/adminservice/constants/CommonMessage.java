package com.carparkingsystem.adminservice.constants;

public enum CommonMessage {
    REGISTRATION_SUCCESSFUL("Registration Successful."),
    LOGIN_SUCCESSFUL("Login Successful."),
    FETCHED_DETAILS_SUCCESSFULLY("Fetched All Parking Slot Details."),
    UPDATED_PARKING_DETAILS_SUCCESSFULLY("Updated Parking Details Successfully.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}