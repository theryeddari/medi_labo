package com.thery.report.controller;

import com.thery.report.exception.ReportServiceException.RiskEstimationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestReportControllerAdvice {

    private final ReportControllerAdvice reportControllerAdvice = new ReportControllerAdvice();

    @Test
    public void testHandleRiskEstimationException() {

        RiskEstimationException ex = new RiskEstimationException(new RuntimeException());

        ResponseEntity<String> response = reportControllerAdvice.handleRiskEstimationException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}