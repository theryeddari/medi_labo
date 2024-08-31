package com.thery.patient.dto;

/**
 * Data Transfer Object for Clientele Identity.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClienteleIdentityDto {

    /**
     * The unique identifier for the patient.
     */
    int patientId;

    /**
     * The name of the patient.
     */
    String name;

    /**
     * The username associated with the patient.
     */
    String username;

    public ClienteleIdentityDto() {
        //lombok constructor
    }
}