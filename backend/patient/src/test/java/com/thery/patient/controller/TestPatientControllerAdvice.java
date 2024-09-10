package com.thery.patient.controller;

import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.thery.patient.exception.PatientServiceException.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPatientControllerAdvice {

    private final PatientControllerAdvice advice = new PatientControllerAdvice();

    @Test
    public void testHandleFindClienteleException() {
        FindClienteleException ex = new FindClienteleException(new RuntimeException());

        ResponseEntity<String> response = advice.handleFindClienteleException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testHandleFindPatientException() {
        FindPatientException ex = new FindPatientException(new RuntimeException());

        ResponseEntity<String> response = advice.handleFindPatientException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testHandleFindPatientExceptionWithNotFoundFoundException() {
        FindPatientException ex = new FindPatientException(new PatientNotFoundException());

        ResponseEntity<String> response = advice.handleFindPatientException(ex);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testHandleSavePatientException() {
        SavePatientException ex = new SavePatientException(new RuntimeException());

        ResponseEntity<String> response = advice.handleSavePatientException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testHandleMethodArgumentNotValidException() {
        ConstraintViolationException ex = new ConstraintViolationException(null);

        ResponseEntity<String> response = advice.handleValidationExceptions(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}