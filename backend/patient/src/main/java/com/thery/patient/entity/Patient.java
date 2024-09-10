package com.thery.patient.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Represents a patient entity in the system.
 * This class is mapped to the "patient" table in the database.
 * It contains information about a patient such as name, username, birthdate, gender, phone number, and address.
 */
@Getter
@Setter
@Table(name = "patient")
@Entity
public class Patient {

    /**
     * Unique identifier for the patient.
     * This field is auto-generated and serves as the primary key.
     */
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "patientId", nullable = false)
    private int id;

    /**
     * The name of the patient.
     * This field cannot be null and has a maximum length of 45 characters.
     */
    @Size(max = 45)
    @NotBlank
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    /**
     * The username of the patient.
     * This field cannot be null and has a maximum length of 45 characters.
     */
    @Size(max = 45)
    @NotBlank
    @Column(name = "username", nullable = false, length = 45)
    private String username;

    /**
     * The birthdate of the patient.
     * This field cannot be null.
     */
    @PastOrPresent
    @Column(name = "birthdate", nullable = false)
    private Timestamp birthdate;

    /**
     * The gender of the patient.
     * This field cannot be null and has a maximum length of 45 characters.
     */
    @Size(max = 45)
    @NotBlank
    @Column(name = "gender", nullable = false, length = 45)
    private String gender;

    /**
     * The phone number of the patient.
     * This field can be null and has a maximum length of 15 characters.
     */
    @Size(max = 15)
    @Column(name = "phone", length = 15)
    private String phone;

    /**
     * The address of the patient.
     * This field can be null and has a maximum length of 100 characters.
     */
    @Size(max = 100)
    @Column(name = "adresse", length = 100)
    private String adresse;
}
