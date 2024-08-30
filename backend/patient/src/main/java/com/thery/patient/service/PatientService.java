package com.thery.patient.service;

import com.thery.patient.dto.ClienteleIdentityDto;
import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.entity.Patient;
import com.thery.patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.thery.patient.exception.PatientServiceException.FindClienteleException;

/**
 * Service class for handling patient operations.
 */
@Service
@Transactional
public class PatientService {
    private static final Logger logger = LogManager.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    /**
     * Constructor for ClientService.
     *
     * @param patientRepository the patient repository
     */
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public ClienteleResponse findClientele() throws FindClienteleException {
        try {
            List<ClienteleIdentityDto> clienteleIdentityDto = new ArrayList<>();
            List<Patient> patients = patientRepository.findAll();
            patients.forEach(patient -> clienteleIdentityDto.add(new ClienteleIdentityDto(patient.getId(), patient.getName(), patient.getUsername())));
            return new ClienteleResponse(clienteleIdentityDto);
        } catch (Exception e) {
            throw new FindClienteleException(e);
        }
    }
}
