package com.thery.report.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thery.report.dto.ReportResponse;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
public class ITReportController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${MEDILABO_USER}")
    private String medilaboUser;

    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;

    @Test
    public void testRiskEstimation_NoRisk() throws Exception {
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        // Test for no risk scenario
        var result = mockMvc.perform(get("/report/{patientId}", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authHeader))
                .andExpect(status().isOk())
                .andReturn();

        // Parse JSON response using ObjectMapper
        String content = result.getResponse().getContentAsString();
        ReportResponse reportResponse = objectMapper.readValue(content, ReportResponse.class);

        // Assert the expected value
        assertEquals("None", reportResponse.getRiskEstimation());
    }

    @Test
    public void testRiskEstimation_Borderline() throws Exception {

        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        // Test for borderline risk scenario
        var result = mockMvc.perform(get("/report/{patientId}", "2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authHeader))
                .andExpect(status().isOk())
                .andReturn();

        // Parse JSON response using ObjectMapper
        String content = result.getResponse().getContentAsString();
        ReportResponse reportResponse = objectMapper.readValue(content, ReportResponse.class);

        // Assert the expected value
        assertEquals("Borderline", reportResponse.getRiskEstimation());
    }

    @Test
    public void testRiskEstimation_Danger_Man() throws Exception {
        // Test for danger risk scenario for a man
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        var result = mockMvc.perform(get("/report/{patientId}", "3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authHeader))
                .andExpect(status().isOk())
                .andReturn();

        // Parse JSON response using ObjectMapper
        String content = result.getResponse().getContentAsString();
        ReportResponse reportResponse = objectMapper.readValue(content, ReportResponse.class);

        // Assert the expected value
        assertEquals("Danger", reportResponse.getRiskEstimation());
    }

    @Test
    public void testRiskEstimation_EarlyOnset() throws Exception {
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        // Test for early onset risk scenario
        var result = mockMvc.perform(get("/report/{patientId}", "4")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", authHeader))
                .andExpect(status().isOk())
                .andReturn();

        // Parse JSON response using ObjectMapper
        String content = result.getResponse().getContentAsString();
        ReportResponse reportResponse = objectMapper.readValue(content, ReportResponse.class);

        // Assert the expected value
        assertEquals("Early onset", reportResponse.getRiskEstimation());
    }
}
