package com.thery.report.controller;

import com.thery.report.dto.ReportResponse;
import com.thery.report.exception.ReportServiceException.RiskEstimationException;
import com.thery.report.service.ReportService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

        when(reportService.riskEstimation(patientId)).thenReturn(mockResponse);

        ReportResponse result = reportController.getReport(patientId);

        verify(reportService).riskEstimation(patientId);
        assertEquals(mockResponse, result);
    }

}
