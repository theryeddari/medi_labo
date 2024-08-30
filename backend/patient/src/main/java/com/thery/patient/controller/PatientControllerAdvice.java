package com.thery.patient.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.thery.patient.exception.PatientServiceException.FindClienteleException;

/**
 * Controller advice to handle exceptions thrown by PatientController.
 */
@ControllerAdvice
public class PatientControllerAdvice {
    private static final Logger logger = LogManager.getLogger(PatientControllerAdvice.class);

    /**
     * Handles GetProfileException and logs the error message.
     *
     * @param ex The exception object.
     * @return ResponseEntity with an error message and HTTP status code INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(FindClienteleException.class)
    public ResponseEntity<String> handleFindClienteleException(FindClienteleException ex) {
        logger.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
