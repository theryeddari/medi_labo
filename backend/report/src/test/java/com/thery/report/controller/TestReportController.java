package com.thery.report.controller;

import com.thery.report.dto.ReportResponse;
import com.thery.report.exception.ReportServiceException.RiskEstimationException;
import com.thery.report.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestReportController {

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportService reportService;

    @Test
    void getReport_success() throws RiskEstimationException {
        String patientId = "123";
        ReportResponse mockResponse = new ReportResponse("none");

        // Configure the mock to return a Mono with the mockResponse
        when(reportService.riskEstimation(patientId)).thenReturn(Mono.just(mockResponse));

        // Call the method to test
        Mono<ReportResponse> result = reportController.getReport(patientId);

        // Verify the service method was called
        verify(reportService).riskEstimation(patientId);

        ReportResponse response = result.block();

        // Verify the result
        assertNotNull(response);
        assertEquals(mockResponse, response);
    }

}
