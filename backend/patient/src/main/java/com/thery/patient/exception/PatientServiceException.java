package com.thery.patient.exception;

import static com.thery.patient.constant.ConstantException.*;

public class PatientServiceException extends Exception {
    public PatientServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PatientServiceException(String message) {
        super(message);
    }


    public static class FindClienteleException extends PatientServiceException {
        public FindClienteleException(Exception cause) {
            super(FIND_CLIENTELE_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);
        }
    }

    public static class FindPatientException extends PatientServiceException {
        public FindPatientException(Exception cause) {
            super(FIND_PATIENT_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);

        }
    }

    public static class PatientNotFoundException extends PatientServiceException {
        public PatientNotFoundException() {
            super(PATIENT_NOT_FOUND_EXCEPTION);
        }
    }
}
