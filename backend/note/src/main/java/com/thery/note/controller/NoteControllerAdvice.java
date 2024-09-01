package com.thery.note.controller;

import com.thery.note.exception.NoteServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.thery.note.exception.NoteServiceException.FindNoteException;
import static com.thery.note.exception.NoteServiceException.SaveNoteException;

/**
 * Controller advice to handle exceptions thrown by NoteController.
 */
@ControllerAdvice
public class NoteControllerAdvice {
    private static final Logger logger = LogManager.getLogger(NoteControllerAdvice.class);


    /**
     * Handles FindNoteException and logs the error message.
     *
     * @param ex The exception object.
     * @return ResponseEntity with an error message and HTTP status code INTERNAL_SERVER_ERROR or NO CONTENT.
     */
    @ExceptionHandler(NoteServiceException.FindNoteException.class)
    public ResponseEntity<String> handleFindFollowNoteException(FindNoteException ex) {
        logger.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles SaveNoteException and logs the error message.
     *
     * @param ex The exception object.
     * @return ResponseEntity with an error message and HTTP status code INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(SaveNoteException.class)
    public ResponseEntity<String> handleSaveNoteException(SaveNoteException ex) {
        logger.error("{}", ex.getMessage());
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}