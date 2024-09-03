package com.thery.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * Represents the response containing a list of note.
 */

@AllArgsConstructor
@Getter
public class NotesResponse {
    List<NoteResponse> noteResponseList;

    /**
     * Default constructor for NotesResponse.
     */
    public NotesResponse() {
        //lombok constructor
    }
}