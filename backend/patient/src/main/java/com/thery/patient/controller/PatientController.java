package com.thery.patient.controller;

import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.dto.PatientResponse;
import com.thery.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static com.thery.patient.exception.PatientServiceException.FindClienteleException;
import static com.thery.patient.exception.PatientServiceException.FindPatientException;

/**
 * Controller class for managing client dashboard APIs.
 */
@RestController
public class PatientController {

    private static final Logger logger = LogManager.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/clientele")
    @ResponseStatus(HttpStatus.OK)
    public ClienteleResponse getClientele() throws FindClienteleException {
        logger.info("Fetching clientele profiles");
        ClienteleResponse profileClientResponse = patientService.findClientele();
        logger.info("clientele profiles fetched successfully");
        return profileClientResponse;
    }

    @GetMapping("/patient/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public PatientResponse getPatient(@PathVariable String patientId) throws FindPatientException {
        logger.info("Fetching patient profiles");
        PatientResponse patientResponse = patientService.findPatient(patientId);
        logger.info("clientele patient fetched successfully");
        return patientResponse;
    }

}
