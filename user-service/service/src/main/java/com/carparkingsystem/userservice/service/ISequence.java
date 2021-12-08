package com.carparkingsystem.userservice.service;

import com.carparkingsystem.userservice.exception.SequenceException;

public interface ISequence {
    long getNextSequenceId(String key) throws SequenceException;
}
