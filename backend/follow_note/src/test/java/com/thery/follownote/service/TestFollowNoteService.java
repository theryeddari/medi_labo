package com.thery.follownote.service;

import com.thery.follownote.document.Note;
import com.thery.follownote.dto.FollowNoteResponse;
import com.thery.follownote.dto.NoteRequest;
import com.thery.follownote.dto.NoteResponse;
import com.thery.follownote.repository.FollowNoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.thery.follownote.exception.FollowNoteServiceException.FindFollowNoteException;
import static com.thery.follownote.exception.FollowNoteServiceException.SaveNoteException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestFollowNoteService {

    @Mock
    private FollowNoteRepository followNoteRepository;

    @InjectMocks
    private FollowNoteService followNoteService;

    @Test
    public void testFindFollowNote_Success() throws FindFollowNoteException {
        String patientId = "1";
        Note note = new Note();
        note.setDate(LocalDateTime.now());
        note.setNote("Patient follow-up note");
        note.setPatientId(patientId);

        List<Note> notes = List.of(note);

        when(followNoteRepository.findFollowNoteByPatientId(patientId)).thenReturn(notes);

        FollowNoteResponse response = followNoteService.findFollowNote(patientId);

        Assertions.assertEquals(1, response.getNoteResponseList().size());
        Assertions.assertEquals(note.getNote(), response.getNoteResponseList().getFirst().getNote());
        verify(followNoteRepository, times(1)).findFollowNoteByPatientId(patientId);
    }

    @Test
    public void testFindFollowNote_ThrowsException() {
        String patientId = "123";

        when(followNoteRepository.findFollowNoteByPatientId(patientId)).thenThrow(new RuntimeException());

        assertThrows(FindFollowNoteException.class, () -> followNoteService.findFollowNote(patientId));
    }

    @Test
    public void testSaveFollowNote_Success() throws SaveNoteException {
        String patientId = "123";
        LocalDateTime noteDate = LocalDateTime.now();
        NoteRequest noteRequest = new NoteRequest(patientId, noteDate, "New follow-up note");

        Note note = new Note();
        note.setDate(noteDate);
        note.setNote("New follow-up note");
        note.setPatientId(patientId);

        when(followNoteRepository.save(any(Note.class))).thenReturn(note);

        NoteResponse response = followNoteService.saveFollowNote(noteRequest);

        Assertions.assertEquals(note.getNote(), response.getNote());
        Assertions.assertEquals(note.getDate(), response.getDate());
        verify(followNoteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void testSaveFollowNote_ThrowsException() {
        String patientId = "123";
        LocalDateTime noteDate = LocalDateTime.now();
        NoteRequest noteRequest = new NoteRequest(patientId, noteDate, "New follow-up note");

        when(followNoteRepository.save(any(Note.class))).thenThrow(new RuntimeException());

        assertThrows(SaveNoteException.class, () -> followNoteService.saveFollowNote(noteRequest));
    }
}
