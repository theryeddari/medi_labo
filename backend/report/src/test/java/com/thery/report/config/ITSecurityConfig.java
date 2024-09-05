package com.thery.report.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;


@SpringBootTest
@AutoConfigureWebTestClient(timeout = "36000")
public class ITSecurityConfig {

    @Value("${MEDILABO_USER}")
    private String medilaboUser;

    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;

    @Value("${ROUTE_REPORT}")
    private String ROUTE_REPORT;


    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testAuthenticationAuthorized() {
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        webTestClient.get()
                .uri(ROUTE_REPORT + "/report/1")
                .header("Authorization", authHeader)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testAuthenticationUnauthorized() {

        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + "wrong").getBytes());

        webTestClient.get()
                .uri("/report/1")
                .header("Authorization", authHeader)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
