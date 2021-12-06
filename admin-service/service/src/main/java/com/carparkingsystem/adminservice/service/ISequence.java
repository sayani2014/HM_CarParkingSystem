package com.carparkingsystem.adminservice.service;

import com.carparkingsystem.adminservice.exception.SequenceException;

public interface ISequence {
    long getNextSequenceId(String key) throws SequenceException;
}
