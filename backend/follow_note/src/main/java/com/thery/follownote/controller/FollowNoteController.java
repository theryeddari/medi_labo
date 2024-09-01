package com.thery.follownote.controller;


import com.thery.follownote.dto.FollowNoteResponse;
import com.thery.follownote.dto.NoteRequest;
import com.thery.follownote.dto.NoteResponse;
import com.thery.follownote.service.FollowNoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.thery.follownote.exception.FollowNoteServiceException.FindFollowNoteException;
import static com.thery.follownote.exception.FollowNoteServiceException.SaveNoteException;

/**
 * Controller class for managing client dashboard APIs.
 * This class is a utility for handling follownote-related actions and responses.
 */
@RestController
public class FollowNoteController {

    private static final Logger logger = LogManager.getLogger(FollowNoteController.class);

    @Autowired
    private FollowNoteService followNoteService;


    /**
     * Fetches a followNote's profile based on followNote ID.
     *
     * @param patientId the ID of the follow Note to be fetched.
     * @return FollowNoteResponse containing the followNote's profile.
     * @throws FindFollowNoteException if there is an error finding the followNote.
     */
    @GetMapping("/notes/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public FollowNoteResponse getFollowNote(@PathVariable String patientId) throws FindFollowNoteException {
        logger.info("Fetching followNote profiles");
        FollowNoteResponse followNoteResponse = followNoteService.findFollowNote(patientId);
        logger.info("Patient followNote fetched successfully");
        return followNoteResponse;
    }

    /**
     * Updates a followNote's profile.
     *
     * @param noteRequest the request object containing followNote information.
     * @return FollowNoteResponse containing the updated followNote profile.
     * @throws SaveNoteException if there is an error saving the followNote.
     */
    @PostMapping("/note")
    @ResponseStatus(HttpStatus.OK)
    public NoteResponse updateFollowNote(@RequestBody NoteRequest noteRequest) throws SaveNoteException {
        logger.info("save note profiles");
        NoteResponse note = followNoteService.saveFollowNote(noteRequest);
        logger.info(" Patient note saved successfully");
        return note;
    }

}