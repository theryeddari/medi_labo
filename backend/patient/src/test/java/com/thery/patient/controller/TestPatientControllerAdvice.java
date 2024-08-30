package com.thery.patient.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.thery.patient.exception.PatientServiceException.FindClienteleException;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPatientControllerAdvice {

    private final PatientControllerAdvice advice = new PatientControllerAdvice();

    @Test
    public void testHandleFindClienteleException() {
        FindClienteleException ex = new FindClienteleException(new RuntimeException());

        ResponseEntity<String> response = advice.handleFindClienteleException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}