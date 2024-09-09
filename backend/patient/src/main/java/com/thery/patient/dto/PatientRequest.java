/**
 * Data Transfer Object for patient requests.
 * This class represents the details of a patient.
 */
package com.thery.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * PatientRequest is a Data Transfer Object used for transferring patient information.
 */
@AllArgsConstructor
@Getter
public class PatientRequest {

    /**
     * The unique identifier for the patient.
     */
    int patientId;

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

    public PatientRequest() {
        //lombok constructor
    }
}