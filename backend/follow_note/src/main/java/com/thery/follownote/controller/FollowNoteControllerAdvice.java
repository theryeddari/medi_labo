package com.thery.follownote.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.thery.follownote.exception.FollowNoteServiceException.FindFollowNoteException;
import static com.thery.follownote.exception.FollowNoteServiceException.SaveNoteException;

/**
 * Controller advice to handle exceptions thrown by FollowNoteController.
 */
@ControllerAdvice
public class FollowNoteControllerAdvice {
    private static final Logger logger = LogManager.getLogger(FollowNoteControllerAdvice.class);


    /**
     * Handles FindFollowNoteException and logs the error message.
     *
     * @param ex The exception object.
     * @return ResponseEntity with an error message and HTTP status code INTERNAL_SERVER_ERROR or NO CONTENT.
     */
    @ExceptionHandler(FindFollowNoteException.class)
    public ResponseEntity<String> handleFindFollowNoteException(FindFollowNoteException ex) {
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