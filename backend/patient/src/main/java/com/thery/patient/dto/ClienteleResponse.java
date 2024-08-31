package com.thery.patient.dto;

/**
 * Represents the response containing a list of clientele identities.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class ClienteleResponse {
    List<ClienteleIdentityDto> clienteleIdentity;

    /**
     * Default constructor for ClienteleResponse.
     */
    public ClienteleResponse() {
        //lombok constructor
    }
}