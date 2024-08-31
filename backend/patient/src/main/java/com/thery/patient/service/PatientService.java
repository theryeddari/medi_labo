package com.thery.patient.service;

import com.thery.patient.dto.ClienteleIdentityDto;
import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.dto.PatientRequest;
import com.thery.patient.dto.PatientResponse;
import com.thery.patient.entity.Patient;
import com.thery.patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.thery.patient.exception.PatientServiceException.*;

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

    /**
     * Finds all clientele and returns their identities.
     *
     * @return a response containing a list of clientele identities
     * @throws FindClienteleException if an error occurs while finding clientele
     */
    public ClienteleResponse findClientele() throws FindClienteleException {
        try {
            logger.info("Finding all clientele.");
            List<ClienteleIdentityDto> clienteleIdentityDto = new ArrayList<>();
            List<Patient> patients = patientRepository.findAll();
            patients.forEach(patient -> clienteleIdentityDto.add(new ClienteleIdentityDto(patient.getId(), patient.getName(), patient.getUsername())));
            logger.info("Successfully found clientele.");
            return new ClienteleResponse(clienteleIdentityDto);
        } catch (Exception e) {
            logger.error("Error finding clientele: {}", e.getMessage());
            throw new FindClienteleException(e);
        }
    }

    /**
     * Finds a patient by their ID.
     *
     * @param patientId the ID of the patient to find
     * @return the response containing patient details
     * @throws FindPatientException if an error occurs while finding the patient
     */
    public PatientResponse findPatient(String patientId) throws FindPatientException {
        try {
            logger.info("Finding patient with ID: {}", patientId);
            Optional<Patient> patient = patientRepository.findById(Integer.parseInt(patientId));
            if (patient.isPresent()) {
                logger.debug("Patient found: {}", patient.get().getName());
                return new PatientResponse(patient.get().getName(),
                        patient.get().getUsername(),
                        patient.get().getBirthdate(),
                        patient.get().getGender(),
                        patient.get().getAdresse(),
                        patient.get().getPhone());
            } else {
                logger.warn("Patient not found with ID: {}", patientId);
                throw new PatientNotFoundException();
            }
        } catch (Exception e) {
            logger.error("Error finding patient: {}", e.getMessage());
            throw new FindPatientException(e);
        }
    }

    /**
     * Saves a new patient.
     *
     * @param patientRequest the request containing patient details to save
     * @return the response containing saved patient details
     * @throws SavePatientException if an error occurs while saving the patient
     */
    public PatientResponse savePatient(PatientRequest patientRequest) throws SavePatientException {
        try {
            logger.info("Saving new patient: {}", patientRequest.getName());
            Patient patient = new Patient();
            patient.setName(patientRequest.getName());
            patient.setUsername(patientRequest.getUsername());
            patient.setBirthdate(patientRequest.getBirthdate());
            patient.setGender(patientRequest.getGender());
            patient.setId(patientRequest.getPatientId());
            patient.setPhone(patientRequest.getPhone());
            patient.setAdresse(patientRequest.getAddress());

            Patient patientSaved = patientRepository.save(patient);
            logger.info("Successfully saved patient: {}", patientSaved.getName());
            return new PatientResponse(patientSaved.getName(), patientSaved.getUsername(), patientSaved.getBirthdate(), patientSaved.getGender(), patientSaved.getAdresse(), patientSaved.getPhone());
        } catch (Exception e) {
            logger.error("Error saving patient: {}", e.getMessage());
            throw new SavePatientException(e);
        }
    }
}