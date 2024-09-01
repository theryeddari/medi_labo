package com.thery.follownote.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Represents the response containing a list of note.
 */

@AllArgsConstructor
@Getter
public class FollowNoteResponse {
    List<NoteResponse> noteResponseList;

    /**
     * Default constructor for FollowNoteResponse.
     */
    public FollowNoteResponse() {
        //lombok constructor
    }
}