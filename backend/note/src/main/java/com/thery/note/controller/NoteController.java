package com.thery.note.controller;


import com.thery.note.dto.NoteRequest;
import com.thery.note.dto.NoteResponse;
import com.thery.note.dto.NotesResponse;
import com.thery.note.service.FollowNoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.thery.note.exception.NoteServiceException.FindNoteException;
import static com.thery.note.exception.NoteServiceException.SaveNoteException;

/**
 * Controller class for managing client dashboard APIs.
 * This class is a utility for handling note-related actions and responses.
 */
@RestController
public class NoteController {

    private static final Logger logger = LogManager.getLogger(NoteController.class);

    @Autowired
    private FollowNoteService notesService;


    /**
     * Fetches a notes's profile based on notes ID.
     *
     * @param patientId the ID of the  Notes to be fetched.
     * @return NotesResponse containing the notes's profile.
     * @throws FindNoteException if there is an error finding the notes.
     */
    @GetMapping("/notes/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public NotesResponse getNotes(@PathVariable String patientId) throws FindNoteException {
        logger.info("Fetching notes profiles");
        NotesResponse notesResponse = notesService.findNotes(patientId);
        logger.info("Patient notes fetched successfully");
        return notesResponse;
    }

    /**
     * Updates a notes's profile.
     *
     * @param noteRequest the request object containing notes information.
     * @return note containing the updated notes profile.
     * @throws SaveNoteException if there is an error saving the notes.
     */
    @PostMapping("/note")
    @ResponseStatus(HttpStatus.OK)
    public NoteResponse addNote(@RequestBody NoteRequest noteRequest) throws SaveNoteException {
        logger.info("save note profiles");
        NoteResponse note = notesService.saveNote(noteRequest);
        logger.info(" Patient note saved successfully");
        return note;
    }

}