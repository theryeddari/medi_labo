package com.thery.report.service;

import com.thery.report.dto.NotesResponse;
import com.thery.report.dto.PatientResponse;
import com.thery.report.dto.ReportResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;

import static com.thery.report.exception.ReportServiceException.RiskEstimationException;
import static com.thery.report.util.RiskCalculatorUtil.calculateAge;
import static com.thery.report.util.RiskCalculatorUtil.calculateRisk;

/******************************************************************************
 * ReportService is a service class that handles the risk estimation process
 * for patients based on their information and notes. It interacts with web
 * clients to retrieve patient data and related notes, then calculates the
 * risk based on this information.
 ******************************************************************************/
@Service
public class ReportService {

    private final WebClient webGateway;

    @Value("${MEDILABO_USER}")
    private String medilaboUser;

    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;

    /**
     * Constructor for ReportService.
     *
     * @param webGateway WebClient for accessing gateway .
     */
    public ReportService(WebClient webGateway) {
        this.webGateway = webGateway;
    }

    /**
     * Estimates the risk for a given patient based on their ID.
     *
     * @param patientId The ID of the patient.
     * @return A ReportResponse containing the estimated risk.
     * @throws RiskEstimationException if an error occurs during risk estimation.
     */
    public ReportResponse riskEstimation(String patientId) throws RiskEstimationException {
        try {
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

            Mono<PatientResponse> monoPatientResponse = webGateway.get()
                    .uri("/api/clientele/patient/{patientId}", patientId)
                    .header("Authorization", authHeader)
                    .retrieve()
                    .bodyToMono(PatientResponse.class);

            Mono<NotesResponse> monoFollowNoteResponse = webGateway.get()
                    .uri("/api/clientele/patient/notes/{patientId}", patientId)
                    .header("Authorization", authHeader)
                    .retrieve()
                    .bodyToMono(NotesResponse.class);

            String risk = Mono.zip(monoPatientResponse, monoFollowNoteResponse)
                    .map(concatenate -> {
                        PatientResponse patientResponse = concatenate.getT1();
                        NotesResponse notesResponse = concatenate.getT2();

                        int age = calculateAge(patientResponse.getBirthdate());
                        boolean isMan = patientResponse.getGender().equals("M");

                        return calculateRisk(age, isMan, notesResponse);
                    }).block();

            return new ReportResponse(risk);
        } catch (Exception e) {
            throw new RiskEstimationException(e);
        }
    }
}