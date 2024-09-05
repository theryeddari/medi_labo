package com.thery.gateway.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;

@SpringBootTest
@AutoConfigureWebTestClient
public class ITGateway {

    String patientId = "1";
    @Autowired
    private WebTestClient webTestClient;
    @Value("${MEDILABO_USER}")
    private String medilaboUser;
    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;
    @Value("${ROUTE_GATEWAY}")
    private String ROUTE_GATEWAY;

    private String getAuthHeader() {
        return "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());
    }

    @Test
    public void testLoginCheckRoute() {
        webTestClient.get()
                .uri(ROUTE_GATEWAY + "/api/auth")
                .header("Authorization", getAuthHeader())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testPatientListRoute() {
        webTestClient.get()
                .uri(ROUTE_GATEWAY + "/api/clientele")
                .header("Authorization", getAuthHeader())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testPatientDetailsRoute() {

        webTestClient.get()
                .uri(ROUTE_GATEWAY + "/api/clientele/patient/" + patientId)
                .header("Authorization", getAuthHeader())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testPatientUpdateRoute() {
        String patientRequestJson = """
                {
                  "patientId": 5,
                  "name": "dupont",
                  "username": "jdupont",
                  "birthdate": "1990-01-01T00:00:00",
                  "gender": "M",
                  "address": "123 Main St",
                  "phone": "1234567890"
                }""";
        webTestClient.post()
                .uri(ROUTE_GATEWAY + "/api/clientele/patient")
                .header("Authorization", getAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(patientRequestJson)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testNotesListRoute() {

        webTestClient.get()
                .uri(ROUTE_GATEWAY + "/api/clientele/patient/notes/" + patientId)
                .header("Authorization", getAuthHeader())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testNoteAddRoute() {
        String noteRequestJson = """
                {
                  "patientId": 5,
                  "date": "1990-01-01T00:00:00",
                  "note": "test note"
                }""";
        webTestClient.post()
                .uri(ROUTE_GATEWAY + "/api/clientele/patient/notes/note")
                .header("Authorization", getAuthHeader())
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(noteRequestJson)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testReportRiskRoute() {

        webTestClient.get()
                .uri("/api/clientele/patient/notes/report/" + patientId)
                .header("Authorization", getAuthHeader())
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void testFallbackRoute() {
        webTestClient.get()
                .uri(ROUTE_GATEWAY + "/nonexistent/path")
                .header("Authorization", getAuthHeader())
                .exchange()
                .expectStatus().isEqualTo(502);
    }
}
