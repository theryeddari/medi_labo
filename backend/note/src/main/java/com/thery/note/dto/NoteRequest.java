package com.thery.note.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Represents a response containing note information.
 */
@AllArgsConstructor
@Getter
public class NoteRequest {

    /**
     * The birthdate of the patient.
     */
    String patientId;

    /**
     * The birthdate of the patient.
     */
    Timestamp date;

    /**
     * The name of the patient.
     */
    String note;

    /**
     * Default constructor for NoteResponse.
     * This constructor is provided by Lombok.
     */
    public NoteRequest() {
        //lombok constructor
    }
}
