package com.thery.note.service;

import com.thery.note.document.Note;
import com.thery.note.dto.NoteRequest;
import com.thery.note.dto.NoteResponse;
import com.thery.note.dto.NotesResponse;
import com.thery.note.repository.NoteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.thery.note.exception.NoteServiceException.FindNoteException;
import static com.thery.note.exception.NoteServiceException.SaveNoteException;

/**
 * service class for handling followNote operations.
 */

@Service
public class FollowNoteService {
    private static final Logger logger = LogManager.getLogger(FollowNoteService.class);

    private final NoteRepository noteRepository;

    /**
     * Constructor for ClientService.
     *
     * @param noteRepository the followNote repository
     */
    public FollowNoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }


    /**
     * Finds a followNote by their ID.
     *
     * @param patientId the ID of the followNote to find
     * @return the response containing followNote details
     * @throws FindNoteException if an error occurs while finding the followNote
     */
    public NotesResponse findNotes(String patientId) throws FindNoteException {
        try {
            List<NoteResponse> noteResponseList = new ArrayList<>();
            List<Note> followNote = noteRepository.findFollowNoteByPatientId(patientId);
            followNote.forEach(note -> noteResponseList.add(new NoteResponse(note.getDate(), note.getNote())));
            return new NotesResponse(noteResponseList);

        } catch (Exception e) {
            logger.error("Error finding followNote: {}", e.getMessage());
            throw new FindNoteException(e);
        }
    }

    /**
     * Saves a new followNote.
     *
     * @param noteRequest the request containing followNote details to save
     * @return the response containing saved followNote details
     * @throws SaveNoteException if an error occurs while saving the followNote
     */
    public NoteResponse saveNote(NoteRequest noteRequest) throws SaveNoteException {
        try {
            logger.info("Saving new note: {}", noteRequest.getPatientId());
            Note note = new Note();
            note.setDate(noteRequest.getDate());
            note.setNote(noteRequest.getNote());
            note.setPatientId(noteRequest.getPatientId());

            Note noteSaved = noteRepository.save(note);
            logger.info("Successfully saved note: {}", note.getId());
            return new NoteResponse(noteSaved.getDate(), noteSaved.getNote());
        } catch (Exception e) {
            logger.error("Error saving followNote: {}", e.getMessage());
            throw new SaveNoteException(e);
        }
    }
}