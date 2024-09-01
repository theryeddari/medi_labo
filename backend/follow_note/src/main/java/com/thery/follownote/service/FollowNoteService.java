package com.thery.follownote.service;

import com.thery.follownote.document.Note;
import com.thery.follownote.dto.FollowNoteResponse;
import com.thery.follownote.dto.NoteRequest;
import com.thery.follownote.dto.NoteResponse;
import com.thery.follownote.repository.FollowNoteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.thery.follownote.exception.FollowNoteServiceException.FindFollowNoteException;
import static com.thery.follownote.exception.FollowNoteServiceException.SaveNoteException;

/**
 * Service class for handling followNote operations.
 */

@Service
public class FollowNoteService {
    private static final Logger logger = LogManager.getLogger(FollowNoteService.class);

    private final FollowNoteRepository followNoteRepository;

    /**
     * Constructor for ClientService.
     *
     * @param followNoteRepository the followNote repository
     */
    public FollowNoteService(FollowNoteRepository followNoteRepository) {
        this.followNoteRepository = followNoteRepository;
    }


    /**
     * Finds a followNote by their ID.
     *
     * @param patientId the ID of the followNote to find
     * @return the response containing followNote details
     * @throws FindFollowNoteException if an error occurs while finding the followNote
     */
    public FollowNoteResponse findFollowNote(String patientId) throws FindFollowNoteException {
        try {
            List<NoteResponse> noteResponseList = new ArrayList<>();
            List<Note> followNote = followNoteRepository.findFollowNoteByPatientId(patientId);
            followNote.forEach(note -> noteResponseList.add(new NoteResponse(note.getDate(), note.getNote())));
            return new FollowNoteResponse(noteResponseList);

        } catch (Exception e) {
            logger.error("Error finding followNote: {}", e.getMessage());
            throw new FindFollowNoteException(e);
        }
    }

    /**
     * Saves a new followNote.
     *
     * @param noteRequest the request containing followNote details to save
     * @return the response containing saved followNote details
     * @throws SaveNoteException if an error occurs while saving the followNote
     */
    public NoteResponse saveFollowNote(NoteRequest noteRequest) throws SaveNoteException {
        try {
            logger.info("Saving new note: {}", noteRequest.getPatientId());
            Note note = new Note();
            note.setDate(noteRequest.getDate());
            note.setNote(noteRequest.getNote());
            note.setPatientId(noteRequest.getPatientId());

            Note noteSaved = followNoteRepository.save(note);
            logger.info("Successfully saved note: {}", note.getId());
            return new NoteResponse(noteSaved.getDate(), noteSaved.getNote());
        } catch (Exception e) {
            logger.error("Error saving followNote: {}", e.getMessage());
            throw new SaveNoteException(e);
        }
    }
}