package com.thery.note.service;

import com.thery.note.document.Note;
import com.thery.note.dto.NoteRequest;
import com.thery.note.dto.NoteResponse;
import com.thery.note.dto.NotesResponse;
import com.thery.note.exception.NoteServiceException;
import com.thery.note.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.thery.note.exception.NoteServiceException.SaveNoteException;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestNoteService {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private FollowNoteService followNoteService;

    @Test
    public void testFindFollowNote_Success() throws NoteServiceException.FindNoteException {
        String patientId = "1";
        Note note = new Note();
        note.setDate(LocalDateTime.now());
        note.setNote("Patient follow-up note");
        note.setPatientId(patientId);

        List<Note> notes = List.of(note);

        when(noteRepository.findFollowNoteByPatientId(patientId)).thenReturn(notes);

        NotesResponse response = followNoteService.findFollowNote(patientId);

        Assertions.assertEquals(1, response.getNoteResponseList().size());
        Assertions.assertEquals(note.getNote(), response.getNoteResponseList().getFirst().getNote());
        verify(noteRepository, times(1)).findFollowNoteByPatientId(patientId);
    }

    @Test
    public void testFindFollowNote_ThrowsException() {
        String patientId = "123";

        when(noteRepository.findFollowNoteByPatientId(patientId)).thenThrow(new RuntimeException());

        assertThrows(NoteServiceException.FindNoteException.class, () -> followNoteService.findFollowNote(patientId));
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

        when(noteRepository.save(any(Note.class))).thenReturn(note);

        NoteResponse response = followNoteService.saveFollowNote(noteRequest);

        Assertions.assertEquals(note.getNote(), response.getNote());
        Assertions.assertEquals(note.getDate(), response.getDate());
        verify(noteRepository, times(1)).save(any(Note.class));
    }

    @Test
    public void testSaveFollowNote_ThrowsException() {
        String patientId = "123";
        LocalDateTime noteDate = LocalDateTime.now();
        NoteRequest noteRequest = new NoteRequest(patientId, noteDate, "New follow-up note");

        when(noteRepository.save(any(Note.class))).thenThrow(new RuntimeException());

        assertThrows(SaveNoteException.class, () -> followNoteService.saveFollowNote(noteRequest));
    }
}
