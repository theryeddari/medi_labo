package com.thery.patient.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thery.patient.dto.PatientRequest;
import com.thery.patient.dto.PatientResponse;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ITPatientController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

    @Test
    @WithMockUser(username = "doctor")
    public void testUpdatePatient_Success() throws Exception {

        PatientRequest patientRequest = new PatientRequest(1, "dupont", "alice", LocalDateTime.now(), "F", "", "");

        String jsonResponse = mockMvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientRequest))
                )
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        PatientResponse patientResponse = objectMapper.readValue(jsonResponse, PatientResponse.class);

        Assertions.assertEquals(patientRequest.getName(), patientResponse.getName());
        Assertions.assertEquals(patientRequest.getUsername(), patientResponse.getUsername());
    }
}