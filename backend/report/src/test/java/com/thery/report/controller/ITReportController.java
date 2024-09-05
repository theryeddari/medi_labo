package com.thery.report.controller;

import com.thery.report.dto.ReportResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "36000")
public class ITReportController {

    @Autowired
    private WebTestClient webTestClient;

    @Value("${MEDILABO_USER}")
    private String medilaboUser;

    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;

    private String getAuthHeader() {
        return "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());
    }

    @Test
    public void testRiskEstimation_NoRisk() {
        String authHeader = getAuthHeader();

        webTestClient.get()
                .uri("/report/{patientId}", "1")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReportResponse.class)
                .value(reportResponse -> assertEquals("None", reportResponse.getRiskEstimation()));
    }

    @Test
    public void testRiskEstimation_Borderline() {
        String authHeader = getAuthHeader();

        webTestClient.get()
                .uri("/report/{patientId}", "2")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReportResponse.class)
                .value(reportResponse -> assertEquals("Borderline", reportResponse.getRiskEstimation()));
    }

    @Test
    public void testRiskEstimation_Danger_Man() {
        String authHeader = getAuthHeader();

        webTestClient.get()
                .uri("/report/{patientId}", "3")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReportResponse.class)
                .value(reportResponse -> assertEquals("Danger", reportResponse.getRiskEstimation()));
    }

    @Test
    public void testRiskEstimation_EarlyOnset() {
        String authHeader = getAuthHeader();

        webTestClient.get()
                .uri("/report/{patientId}", "4")
                .header(HttpHeaders.AUTHORIZATION, authHeader)
                .exchange()
                .expectStatus().isOk()
                .expectBody(ReportResponse.class)
                .value(reportResponse -> assertEquals("Early onset", reportResponse.getRiskEstimation()));
    }
}
