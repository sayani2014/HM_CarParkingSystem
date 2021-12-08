package com.carparkingsystem.userservice.constant;

public enum CommonMessage {
    BOOKING_SUCCESSFUL("Parking booked successfully.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
