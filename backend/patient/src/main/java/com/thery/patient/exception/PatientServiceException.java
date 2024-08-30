package com.thery.patient.exception;

import static com.thery.patient.constant.ConstantException.FIND_CLIENTELE_EXCEPTION;
import static com.thery.patient.constant.ConstantException.MORE_INFO;

public class PatientServiceException extends Exception {
    public PatientServiceException(String message, Throwable cause) {
        super(message, cause);
    }


    public static class FindClienteleException extends Throwable {
        public FindClienteleException(Exception cause) {
            super(FIND_CLIENTELE_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);
        }
    }
}
