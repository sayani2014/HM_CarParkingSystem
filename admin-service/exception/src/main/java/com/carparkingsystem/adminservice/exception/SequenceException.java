package com.carparkingsystem.adminservice.exception;

public class SequenceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    public SequenceException(String errMsg) {
        this.errMsg = errMsg;
    }

}
