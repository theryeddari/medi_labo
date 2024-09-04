package com.thery.patient.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Base64;


@SpringBootTest
@AutoConfigureMockMvc
public class ITSecurityConfig {

    @Autowired
    private MockMvc mockMvc;

    @Value("${MEDILABO_USER}")
    private String medilaboUser;

    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;


    //WithMockUser password by default its password
    @Test
    public void testAuthenticationAuthorized() throws Exception {
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        mockMvc.perform(MockMvcRequestBuilders.get("/clientele")
                        .header("Authorization", authHeader))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAuthenticationUnauthorized() throws Exception {
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        mockMvc.perform(MockMvcRequestBuilders.get("/clientele")
                        .header("Authorization", authHeader))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
