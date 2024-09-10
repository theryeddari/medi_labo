package com.thery.patient.controller;

import jakarta.validation.ConstraintViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.thery.patient.exception.PatientServiceException.*;

/**
 * Controller advice to handle exceptions thrown by PatientController.
 */
@ControllerAdvice
public class PatientControllerAdvice {
    private static final Logger logger = LogManager.getLogger(PatientControllerAdvice.class);

    /**
     * Handles FindClienteleException and logs the error message.
     *
     * @param ex The exception object.
     * @return ResponseEntity with an error message and HTTP status code INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(FindClienteleException.class)
    public ResponseEntity<String> handleFindClienteleException(FindClienteleException ex) {
        logger.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles FindPatientException and logs the error message.
     *
     * @param ex The exception object.
     * @return ResponseEntity with an error message and HTTP status code INTERNAL_SERVER_ERROR or NO CONTENT.
     */
    @ExceptionHandler(FindPatientException.class)
    public ResponseEntity<String> handleFindPatientException(FindPatientException ex) {
        logger.error("{}", ex.getMessage());
        if (ex.getCause() instanceof PatientNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles SavePatientException and logs the error message.
     *
     * @param ex The exception object.
     * @return ResponseEntity with an error message and HTTP status code INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(SavePatientException.class)
    public ResponseEntity<String> handleSavePatientException(SavePatientException ex) {
        logger.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles exceptions caused by invalid method arguments (e.g., validation errors).
     * Intercepts the MethodArgumentNotValidException and returns a ResponseEntity
     * with a 400 Bad Request status without including any message.
     *
     * @param ex the exception raised when method argument validation fails
     * @return a ResponseEntity with a 400 Bad Request status
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException ex) {
        logger.error("{}", ex.getMessage());
        // Return a ResponseEntity with HTTP 400 (Bad Request) and no body
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}