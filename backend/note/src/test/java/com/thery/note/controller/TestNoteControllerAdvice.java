package com.thery.note.controller;

import com.thery.note.exception.NoteServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNoteControllerAdvice {
    private final NoteControllerAdvice advice = new NoteControllerAdvice();

    @Test
    public void testHandleFindFollowNoteException() {
        NoteServiceException.FindNoteException ex = new NoteServiceException.FindNoteException(new RuntimeException());

        ResponseEntity<String> response = advice.handleFindFollowNoteException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testHandleSaveNoteException() {
        NoteServiceException.SaveNoteException ex = new NoteServiceException.SaveNoteException(new RuntimeException());

        ResponseEntity<String> response = advice.handleSaveNoteException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
