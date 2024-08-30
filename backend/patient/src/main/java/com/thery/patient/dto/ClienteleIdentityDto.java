package com.thery.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClienteleIdentityDto {

    int patientId;
    String name;
    String username;
    public ClienteleIdentityDto() {
        //lombok constructor
    }
}
