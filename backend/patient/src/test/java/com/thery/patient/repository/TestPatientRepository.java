package com.thery.patient.repository;

import com.thery.patient.entity.Patient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestPatientRepository {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void testPatientCreation() {
        Patient patient = new Patient();
        patient.setName("Dupont");
        patient.setUsername("Alice");
        patient.setBirthdate(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        patient.setGender("F");

        patient = patientRepository.save(patient);
        assertTrue(patient.getId() >= 0);
        assertEquals("Dupont", patient.getName());
    }

    @Test
    public void testPatientUpdate() {
        Patient patient = new Patient();
        patient.setName("Dupont");
        patient.setUsername("Alice");
        patient.setBirthdate(LocalDateTime.now());
        patient.setGender("F");
        patient = patientRepository.save(patient);

        patient.setName("Desfleurs");
        assertTrue(patient.getId() >= 0);
        assertEquals("Desfleurs", patient.getName());
    }

    @Test
    public void testFindAllPatients() {
        List<Patient> listResult = patientRepository.findAll();
        assertTrue(listResult.size() > 4);

    }

    @Test
    public void testDeletePatient() {
        Patient patient = new Patient();
        patient.setName("Dupont");
        patient.setUsername("Alice");
        patient.setBirthdate(LocalDateTime.now());
        patient.setGender("F");
        patient = patientRepository.save(patient);

        int patientId = patient.getId();
        patientRepository.delete(patient);
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        assertFalse(patientOptional.isPresent());
    }
}
