package com.thery.patient.controller;

import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.dto.PatientRequest;
import com.thery.patient.dto.PatientResponse;
import com.thery.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static com.thery.patient.exception.PatientServiceException.*;

/**
 * Controller class for managing client dashboard APIs.
 * This class is a utility for handling patient-related actions and responses.
 */
@RestController
public class PatientController {

    private static final Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    /**
     * Fetches the clientele profiles.
     *
     * @return ClienteleResponse containing the clientele profiles.
     * @throws FindClienteleException if there is an error finding clientele.
     */
    @GetMapping("/clientele")
    @ResponseStatus(HttpStatus.OK)
    public ClienteleResponse getClientele() throws FindClienteleException {
        logger.info("Fetching clientele profiles");
        ClienteleResponse profileClientResponse = patientService.findClientele();
        logger.info("clientele profiles fetched successfully");
        return profileClientResponse;
    }

    /**
     * Fetches a patient's profile based on patient ID.
     *
     * @param patientId the ID of the patient to be fetched.
     * @return PatientResponse containing the patient's profile.
     * @throws FindPatientException if there is an error finding the patient.
     */
    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse getPatient(@PathVariable String patientId) throws FindPatientException {
        logger.info("Fetching patient profiles");
        PatientResponse patientResponse = patientService.findPatient(patientId);
        logger.info("clientele patient fetched successfully");
        return patientResponse;
    }

    /**
     * Updates a patient's profile.
     *
     * @param patientRequest the request object containing patient information.
     * @return PatientResponse containing the updated patient profile.
     * @throws SavePatientException if there is an error saving the patient.
     */
    @PostMapping("/patient")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse updatePatient(@RequestBody PatientRequest patientRequest) throws SavePatientException {
        logger.info("save patient profiles");
        PatientResponse patientResponse = patientService.savePatient(patientRequest);
        logger.info("clientele patient saved successfully");
        return patientResponse;
    }

}