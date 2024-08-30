package com.thery.patient.service;

import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.entity.Patient;
import com.thery.patient.exception.PatientServiceException;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestPatientService {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testFindClientele_Success() throws PatientServiceException.FindClienteleException {
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
        assertThrows(PatientServiceException.FindClienteleException.class, () -> patientService.findClientele());
    }
}