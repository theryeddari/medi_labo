package com.thery.report.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Represents a response containing note information.
 */
@AllArgsConstructor
@Getter
public class NoteResponse {


    /**
     * The birthdate of the patient.
     */
    LocalDateTime date;

    /**
     * The name of the patient.
     */
    String note;

    /**
     * Default constructor for NoteResponse.
     * This constructor is provided by Lombok.
     */
    public NoteResponse() {
        //lombok constructor
    }
}
