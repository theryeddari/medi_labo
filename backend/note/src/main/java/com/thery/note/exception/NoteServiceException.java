/**
 * Exception handling for patient service related errors.
 */
package com.thery.note.exception;

import static com.thery.note.constant.ConstantException.*;

/**
 * Base class for exceptions thrown by the PatientService.
 */
public class NoteServiceException extends Exception {

    /**
     * Constructs a new NoteServiceException with the specified detail message and cause.
     *
     * @param message the detail message.
     * @param cause   the cause of the exception.
     */
    public NoteServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception thrown when patients cannot be found.
     */
    public static class FindNoteException extends NoteServiceException {
        /**
         * Constructs a new FindNoteException with the specified cause.
         *
         * @param cause the cause of the exception.
         */
        public FindNoteException(Exception cause) {
            super(FIND_FOLLOW_NOTE_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);

        }
    }

    /**
     * Exception thrown when there is an error saving a patient.
     */
    public static class SaveNoteException extends NoteServiceException {
        /**
         * Constructs a new SaveNoteException with the specified cause.
         *
         * @param cause the cause of the exception.
         */
        public SaveNoteException(Exception cause) {
            super(SAVE_FOLLOW_NOTE_EXCEPTION + cause.getClass() + MORE_INFO + " " + cause.getMessage(), cause);

        }
    }
}