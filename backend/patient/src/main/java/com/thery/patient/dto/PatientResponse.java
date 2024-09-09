package com.thery.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * Represents a response containing patient information.
 */
@AllArgsConstructor
@Getter
public class PatientResponse {

    /**
     * The name of the patient.
     */
    String name;

    /**
     * The username of the patient.
     */
    String username;

    /**
     * The birthdate of the patient.
     */
    Timestamp birthdate;

    /**
     * The gender of the patient.
     */
    String gender;


    /**
     * The address of the patient.
     */
    String address;

    /**
     * The phone number of the patient.
     */
    String phone;

    /**
     * Default constructor for PatientResponse.
     * This constructor is provided by Lombok.
     */
    public PatientResponse() {
        //lombok constructor
    }
}
