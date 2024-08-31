/**
 * Exception handling for patient service related errors.
 */
package com.thery.patient.exception;

import static com.thery.patient.constant.ConstantException.*;

/**
 * Base class for exceptions thrown by the PatientService.
 */
public class PatientServiceException extends Exception {

    /**
     * Constructs a new PatientServiceException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public PatientServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new PatientServiceException with the specified detail message.
     *
     * @param message the detail message.
     */
    public PatientServiceException(String message) {
        super(message);
    }


    /**
     * Exception thrown when clienteles cannot be found.
     */
    public static class FindClienteleException extends PatientServiceException {
        /**
         * Constructs a new FindClienteleException with the specified cause.
         *
         * @param cause the cause of the exception.
         */
        public FindClienteleException(Exception cause) {
            super(FIND_CLIENTELE_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);
        }
    }

    /**
     * Exception thrown when patients cannot be found.
     */
    public static class FindPatientException extends PatientServiceException {
        /**
         * Constructs a new FindPatientException with the specified cause.
         *
         * @param cause the cause of the exception.
         */
        public FindPatientException(Exception cause) {
            super(FIND_PATIENT_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);

        }
    }

    /**
     * Exception thrown when a patient is not found.
     */
    public static class PatientNotFoundException extends PatientServiceException {
        /**
         * Constructs a new PatientNotFoundException.
         */
        public PatientNotFoundException() {
            super(PATIENT_NOT_FOUND_EXCEPTION);
        }
    }

    /**
     * Exception thrown when there is an error saving a patient.
     */
    public static class SavePatientException extends PatientServiceException {
        /**
         * Constructs a new SavePatientException with the specified cause.
         *
         * @param cause the cause of the exception.
         */
        public SavePatientException(Exception cause) {
            super(SAVE_PATIENT_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);

        }
    }
}