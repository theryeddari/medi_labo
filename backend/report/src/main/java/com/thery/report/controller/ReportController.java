/**********************************************
 * ReportController handles requests related to
 * reports for patients.
 **********************************************/

package com.thery.report.controller;

import com.thery.report.dto.ReportResponse;
import com.thery.report.service.ReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.thery.report.exception.ReportServiceException.RiskEstimationException;

/**
 * Controller for managing patient report retrieval.
 */
@RestController
public class ReportController {

    private static final Logger logger = LogManager.getLogger(ReportController.class);

    @Autowired
    ReportService reportService;

    /**
     * Fetches the report for a specific patient identified by their ID.
     *
     * @param patientId the ID of the patient whose report is to be fetched
     * @return ReportResponse containing the patient's report information
     * @throws RiskEstimationException if an error occurs while fetching the report
     */
    @GetMapping("/report/{patientId}")
    public ReportResponse getReport(@PathVariable String patientId) throws RiskEstimationException {
        logger.info("Fetching report patient");
        ReportResponse reportResponse = reportService.riskEstimation(patientId);
        logger.info("Patient report fetched successfully");
        return reportResponse;
    }
}