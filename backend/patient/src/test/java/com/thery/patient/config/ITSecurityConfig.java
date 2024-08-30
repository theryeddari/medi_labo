package com.thery.patient.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
public class ITSecurityConfig {

    @Autowired
    private MockMvc mockMvc;

    @Value("${ROUTE_PATIENT}")
    private String ROUTE_PATIENT;

    //WithMockUser password by default its password
    @Test
    @WithMockUser(username = "doctor")
    public void testAuthenticationAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientele"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(username = "doctor", password = "wrong")
    public void testAuthenticationUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientele"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
