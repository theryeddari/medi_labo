package com.thery.gateway.config;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;


@SpringBootTest
@AutoConfigureWebTestClient
public class SecurityIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Value("${MEDILABO_USER}")
    private String medilaboUser;

    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;

    String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());
    @Value("${ROUTE_GATEWAY}")
    private String ROUTE_GATEWAY;

    //for configuration integration tests, there is no direct component to test or external
    // service, so we will do our tests by banning ourselves on a route that does not exist (BAD_GATEWAY).
    @Disabled
    @Test
    public void testCorsConfiguration() {
        webTestClient.get()
                .uri(ROUTE_GATEWAY + "/api/clientele")
                .header("Origin", "${ROUTE_ANGULAR}")
                .header("Authorization", "Basic " + authHeader)
                .header("Access-Control-Request-Method", "GET")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.OK)
                .expectHeader().exists("Access-Control-Allow-Origin")
                .expectHeader().valueEquals("Access-Control-Allow-Origin", "${ROUTE_ANGULAR}")
                .expectHeader().exists("Access-Control-Expose-Headers")
                .expectHeader().valueEquals("Access-Control-Expose-Headers", "Authorization, Content-Type, *")
                .expectHeader().exists("Access-Control-Allow-Credentials")
                .expectHeader().valueEquals("Access-Control-Allow-Credentials", "true");
    }

    @Test
    public void testAuthentication() {
        webTestClient.get()
                .uri(ROUTE_GATEWAY + "/api/gateway/noexist")
                .header("Origin", "${ROUTE_ANGULAR}")
                .header("Authorization", "Basic " + authHeader)
                .header("Access-Control-Request-Method", "GET")
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
