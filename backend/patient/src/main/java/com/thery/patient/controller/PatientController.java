package com.thery.patient.controller;

import com.thery.patient.dto.ClienteleResponse;
import com.thery.patient.exception.PatientServiceException;
import com.thery.patient.service.PatientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public ClienteleResponse getClientele() throws PatientServiceException.FindClienteleException {
        logger.info("Fetching clientele profiles");
        ClienteleResponse profileClientResponse = patientService.findClientele();
        logger.info("clientele profiles fetched successfully");
        return profileClientResponse;
    }

}
