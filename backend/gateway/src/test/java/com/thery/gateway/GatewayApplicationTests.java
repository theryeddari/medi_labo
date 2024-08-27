package com.thery.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GatewayApplicationTests {

    @Value("${angular.url}")
    private String angular;

    @Test
    void contextLoads() {
    }

}
