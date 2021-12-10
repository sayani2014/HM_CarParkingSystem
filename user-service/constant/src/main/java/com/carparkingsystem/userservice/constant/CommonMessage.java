package com.carparkingsystem.userservice.constant;

public enum CommonMessage {
    BOOKING_SUCCESSFUL("Parking booked successfully."),
    PARKING_EXITED("Parking exited successfully."),
    LOGIN_SUCCESSFUL("Logged in successfully.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
