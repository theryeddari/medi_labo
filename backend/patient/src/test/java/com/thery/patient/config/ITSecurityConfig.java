package com.thery.patient.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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


    //WithMockUser password by default its password
    @Test
    public void testAuthenticationAuthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientele")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("doctor:password".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testAuthenticationUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/clientele")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("user:password".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
