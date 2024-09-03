/**
 * Custom exception class for report service-related errors.
 */
package com.thery.report.exception;

import static com.thery.report.constant.constantException.MORE_INFO;
import static com.thery.report.constant.constantException.RISK_ESTIMATION_EXCEPTION;

/**
 * Exception thrown when there is a report service related issue.
 */
public class ReportServiceException extends Exception {
    /**
     * Constructs a new ReportServiceException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public ReportServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception thrown specifically for risk estimation related issues within the report service.
     */
    public static class RiskEstimationException extends ReportServiceException {
        /**
         * Constructs a new RiskEstimationException with the specified cause.
         *
         * @param cause the cause of the exception
         */
        public RiskEstimationException(Exception cause) {
            super(RISK_ESTIMATION_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);
        }
    }
}