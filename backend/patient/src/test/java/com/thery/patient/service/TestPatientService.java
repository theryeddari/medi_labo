package com.thery.patient.service;

import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.dto.PatientRequest;
import com.thery.patient.dto.PatientResponse;
import com.thery.patient.entity.Patient;
import com.thery.patient.repository.PatientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import static com.thery.patient.exception.PatientServiceException.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestPatientService {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testFindClientele_Success() throws FindClienteleException {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setName("Dupont");
        patient.setUsername("Alice");
        patient.setBirthdate(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        patient.setGender("F");

        List<Patient> patients = List.of(patient);

        when(patientRepository.findAll()).thenReturn(patients);

        ClienteleResponse response = patientService.findClientele();

        Assertions.assertEquals(patients.getFirst().getId(), response.getClienteleIdentity().getFirst().getPatientId());
        Assertions.assertEquals(patients.getFirst().getName(), response.getClienteleIdentity().getFirst().getName());
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    public void testFindClientele_ThrowsException() {
        when(patientRepository.findAll()).thenThrow(new RuntimeException());
        assertThrows(FindClienteleException.class, () -> patientService.findClientele());
    }

    @Test
    public void testFindPatient_Success() throws FindPatientException {
        Patient patient = new Patient();
        patient.setId(1);
        patient.setName("Dupont");
        patient.setUsername("Alice");
        patient.setBirthdate(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
        patient.setGender("F");

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));

        PatientResponse response = patientService.findPatient("1");

        Assertions.assertEquals(patient.getName(), response.getName());
        Assertions.assertEquals(patient.getBirthdate(), response.getBirthdate());
        verify(patientRepository, times(1)).findById(1);
    }

    @Test
    public void testFindPatient_ThrowsException() {
        when(patientRepository.findById(1)).thenThrow(new RuntimeException());
        assertThrows(FindPatientException.class, () -> patientService.findPatient("1"));
    }

    @Test
    public void testFindPatient_ThrowsExceptionWithPatientNotFoundException() {
        when(patientRepository.findById(1)).thenReturn(Optional.empty());
        Throwable result = assertThrows(FindPatientException.class, () -> patientService.findPatient("1"));
        Assertions.assertEquals(PatientNotFoundException.class, result.getCause().getClass());
    }

    @Test
    public void testUpdatePatient_Success() throws SavePatientException {
        LocalDateTime birthdate = LocalDateTime.now();
        PatientRequest patientRequest = new PatientRequest(1, "dupont", "alice", birthdate, "F", "", "");

        Patient patient = new Patient();
        patient.setId(1);
        patient.setName("dupont");
        patient.setUsername("alice");
        patient.setBirthdate(birthdate);
        patient.setGender("F");
        patient.setAdresse("");
        patient.setPhone("");

        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientResponse response = patientService.savePatient(patientRequest);

        Assertions.assertEquals(patient.getName(), response.getName());
        Assertions.assertEquals(patient.getBirthdate(), response.getBirthdate());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void testSavePatient_ThrowsException() {
        PatientRequest patientRequest = new PatientRequest(1, "dupont", "alice", LocalDateTime.now(), "F", "", "");


        when(patientRepository.save(any(Patient.class))).thenThrow(new RuntimeException());
        assertThrows(SavePatientException.class, () -> patientService.savePatient(patientRequest));
    }

}