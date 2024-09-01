package com.thery.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class GatewayRouteIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testPatientListRoute() {
        // Perform the GET request to the gateway route with Basic Auth
        WebTestClient.bindToServer()
                .responseTimeout(Duration.ofSeconds(10))
                .build();
        webTestClient.get()
                .uri("http://gateway:8080/api/clientele")
                .headers(headers -> headers.setBasicAuth("doctor", "password"))  // Set Basic Auth credentials
                .exchange()
                .expectStatus().isOk()  // Verify that the status code is 200 OK
                .expectHeader().exists("Content-Type") // Check that the Content-Type header is present
                .expectBody() // Verify the body (you can add more checks as needed)
                .consumeWith(response -> {
                    // Optionally assert further on the response body or headers
                    System.out.println("Response: " + new String(response.getResponseBody()));
                });
    }
}