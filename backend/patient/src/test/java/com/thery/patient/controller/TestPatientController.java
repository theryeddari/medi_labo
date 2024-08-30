package com.thery.patient.controller;

import com.thery.patient.dto.ClienteleIdentityDto;
import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.dto.PatientResponse;
import com.thery.patient.exception.PatientServiceException;
import com.thery.patient.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static com.thery.patient.exception.PatientServiceException.FindClienteleException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestPatientController {
    @InjectMocks
    PatientController patientController;

    @Mock
    private PatientService patientService;

    @Test
    void getClientele_success() throws FindClienteleException {

        ClienteleResponse clienteleResponse = new ClienteleResponse(List.of(new ClienteleIdentityDto(1, "Dupont", "Alice")));

        when(patientService.findClientele()).thenReturn(clienteleResponse);


        ClienteleResponse result = patientController.getClientele();

        verify(patientService).findClientele();
        assertEquals(clienteleResponse, result);
    }

    @Test
    void getPatient_success() throws FindClienteleException, PatientServiceException.FindPatientException {

        PatientResponse patientResponse = new PatientResponse("Dupont", "Alice", LocalDateTime.now(), "F", "", "");

        when(patientService.findPatient("1")).thenReturn(patientResponse);


        PatientResponse result = patientController.getPatient("1");

        verify(patientService).findPatient("1");
        assertEquals(patientResponse, result);
    }
}
