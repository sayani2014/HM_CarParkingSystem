package com.carparkingsystem.userservice.constant;

public enum ExceptionType {
    ID_NOT_FOUND("Unable to get sequence id for key : "),
    PARKING_LOT_FULL("Parking lot is booked. Please select a new slot."),
    PARKING_EXIST("Parking slot already booked.");

    private String message;

    ExceptionType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

