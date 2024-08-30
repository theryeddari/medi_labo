package com.thery.patient.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ITPatientController {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "doctor")
    public void testGetClientele_Success() throws Exception {

        mockMvc.perform(get("/clientele")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(response ->
                        Assertions.assertTrue(Objects.requireNonNull(response.getResponse().getContentAsString()).contains("TestNone")));
    }

    @Test
    @WithMockUser(username = "doctor")
    public void testGetPatient_Success() throws Exception {

        mockMvc.perform(get("/clientele")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("patientId", "1")
                )
                .andExpect(status().isOk())
                .andExpect(response ->
                        Assertions.assertTrue(Objects.requireNonNull(response.getResponse().getContentAsString()).contains("TestNone")));
    }
}