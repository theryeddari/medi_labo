package com.thery.follownote.controller;

import com.thery.follownote.exception.FollowNoteServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFollowNoteControllerAdvice {
    private final FollowNoteControllerAdvice advice = new FollowNoteControllerAdvice();

    @Test
    public void testHandleFindFollowNoteException() {
        FollowNoteServiceException.FindFollowNoteException ex = new FollowNoteServiceException.FindFollowNoteException(new RuntimeException());

        ResponseEntity<String> response = advice.handleFindFollowNoteException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testHandleSaveNoteException() {
        FollowNoteServiceException.SaveNoteException ex = new FollowNoteServiceException.SaveNoteException(new RuntimeException());

        ResponseEntity<String> response = advice.handleSaveNoteException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
