package com.carparkingsystem.carparking.constant;

public enum CommonMessage {
    REGISTRATION_SUCCESSFUL("Registration Successful."),
    FETCHED_DETAILS_SUCCESSFULLY("Fetched Parking Details Successfully."),
    UPDATED_PARKING_DETAILS_SUCCESSFULLY("Updated Parking Details Successfully."),
    UPDATED_PARKING_SLOT_SUCCESSFULLY("Updated Parking Slot Successfully.");

    private String message;

    CommonMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
