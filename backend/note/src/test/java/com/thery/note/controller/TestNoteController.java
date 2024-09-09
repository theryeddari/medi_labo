package com.thery.note.controller;

import com.thery.note.dto.NoteRequest;
import com.thery.note.dto.NoteResponse;
import com.thery.note.dto.NotesResponse;
import com.thery.note.exception.NoteServiceException;
import com.thery.note.service.FollowNoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestNoteController {

    @InjectMocks
    private NoteController noteController;

    @Mock
    private FollowNoteService followNoteService;

    @Test
    void getNotes_success() throws NoteServiceException {
        NotesResponse notesResponse = new NotesResponse(List.of(new NoteResponse(Timestamp.valueOf(LocalDateTime.now()), "surpoids")));

        when(followNoteService.findNotes("1")).thenReturn(notesResponse);

        NotesResponse result = noteController.getNotes("1");

        verify(followNoteService).findNotes("1");
        assertEquals(notesResponse, result);
    }

    @Test
    void addNote_success() throws NoteServiceException.SaveNoteException {
        NoteRequest noteRequest = new NoteRequest("1", Timestamp.valueOf(LocalDateTime.now()), "Some note");
        NoteResponse noteResponse = new NoteResponse(Timestamp.valueOf(LocalDateTime.now()), "Some note");
        when(followNoteService.saveNote(noteRequest)).thenReturn(noteResponse);

        NoteResponse result = noteController.addNote(noteRequest);

        verify(followNoteService).saveNote(noteRequest);
        assertEquals(noteResponse, result);
    }
}