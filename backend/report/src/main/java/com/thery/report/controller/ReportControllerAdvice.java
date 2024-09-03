/************************************************************************************
 * ReportControllerAdvice is a class that handles exceptions for the Report Controller.
 * It provides centralized exception handling for specific exceptions and logs the error messages.
 ************************************************************************************/
package com.thery.report.controller;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.thery.report.exception.ReportServiceException.RiskEstimationException;

@AllArgsConstructor
public class ReportControllerAdvice {

    private static final Logger logger = LogManager.getLogger(ReportControllerAdvice.class);

    /**
     * Handles RiskEstimationException by logging the error message and returning
     * an INTERNAL_SERVER_ERROR response.
     *
     * @param ex the RiskEstimationException thrown
     * @return ResponseEntity with HttpStatus.INTERNAL_SERVER_ERROR
     */
    @ExceptionHandler(RiskEstimationException.class)
    public ResponseEntity<String> handleRiskEstimationException(RiskEstimationException ex) {
        logger.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}