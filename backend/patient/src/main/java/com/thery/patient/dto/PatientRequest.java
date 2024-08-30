package com.thery.patient.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PatientRequest {

    int patientId;
    String name;
    String username;
    LocalDateTime birthdate;
    String gender;
    String address;
    String phone;
    public PatientRequest() {
        //lombok constructor
    }
}
