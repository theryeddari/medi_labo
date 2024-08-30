package com.thery.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PatientResponse {

    String name;
    String username;
    LocalDateTime birthdate;
    String gender;
    String address;
    String phone;
    public PatientResponse() {
        //lombok constructor
    }
}
