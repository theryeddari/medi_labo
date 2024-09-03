package com.thery.report.service;

import com.thery.report.dto.NotesResponse;
import com.thery.report.dto.PatientResponse;
import com.thery.report.dto.ReportResponse;
import com.thery.report.exception.ReportServiceException;
import com.thery.report.util.RiskCalculatorUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class TestReportService {

    private ReportService reportService;
    private WebClient webGateway;

    @Value("${MEDILABO_USER}")
    private String medilaboUser;

    @Value("${MEDILABO_PASSWORD}")
    private String medilaboPassword;

    @BeforeEach
    void setUp() {
        webGateway = mock(WebClient.class);
        reportService = new ReportService(webGateway);
    }

    @Test
    void testRiskEstimation_Success() throws Exception {
        // Given
        String patientId = "1";
        String authHeader = "Basic " + Base64.getEncoder().encodeToString((medilaboUser + ":" + medilaboPassword).getBytes());

        // Mock the PatientResponse and NotesResponse objects
        PatientResponse mockPatientResponse = new PatientResponse("dupont", "alice", LocalDateTime.of(1984, 5, 20, 0, 0), "F", "", "");
        NotesResponse mockNotesResponse = new NotesResponse();

        // Mock the WebClient interactions for patient
        WebClient.RequestHeadersUriSpec<?> uriSpecMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
        WebClient.RequestHeadersSpec<?> headersSpecMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
        WebClient.ResponseSpec responseSpecMock = Mockito.mock(WebClient.ResponseSpec.class);

        // Use doReturn to configure the mocks
        doReturn(uriSpecMock).when(webGateway).get();
        doReturn(headersSpecMock).when(uriSpecMock).uri(anyString(), eq(patientId));
        doReturn(headersSpecMock).when(headersSpecMock).header("Authorization", authHeader);
        doReturn(responseSpecMock).when(headersSpecMock).retrieve();
        doReturn(Mono.just(mockPatientResponse)).doReturn(Mono.just(mockNotesResponse)).when(responseSpecMock).bodyToMono(ArgumentMatchers.<Class<PatientResponse>>notNull());

//        // Mock the WebClient interactions for notes
//        WebClient.RequestHeadersUriSpec<?> uriSpecNoteMock = Mockito.mock(WebClient.RequestHeadersUriSpec.class);
//        WebClient.RequestHeadersSpec<?> headersSpecNoteMock = Mockito.mock(WebClient.RequestHeadersSpec.class);
//        WebClient.ResponseSpec responseSpecNoteMock = Mockito.mock(WebClient.ResponseSpec.class);
//
//        // Use doReturn to configure the mocks
//        doReturn(uriSpecNoteMock).when(webGateway).get();
//        doReturn(headersSpecNoteMock).when(uriSpecNoteMock).uri("/api/clientele/patient/notes/1", patientId);
//        doReturn(headersSpecNoteMock).when(headersSpecNoteMock).header(eq("Authorization"), eq(authHeader));
//        doReturn(responseSpecNoteMock).when(headersSpecNoteMock).retrieve();
//        doReturn(Mono.just(mockNotesResponse)).when(responseSpecNoteMock).bodyToMono(ArgumentMatchers.<Class<NotesResponse>>notNull());

        // Instantiate the service with the mocked WebClients
        ReportService reportService = new ReportService(webGateway);

        // Mock static methods
        try (MockedStatic<RiskCalculatorUtil> mockedStatic = mockStatic(RiskCalculatorUtil.class)) {
            mockedStatic.when(() -> RiskCalculatorUtil.calculateAge(mockPatientResponse.getBirthdate())).thenReturn(40);
            mockedStatic.when(() -> RiskCalculatorUtil.calculateRisk(eq(40), eq(false), eq(mockNotesResponse))).thenReturn("None");

            // When
            ReportResponse reportResponse = reportService.riskEstimation(patientId);

            // Then
            assertEquals("None", reportResponse.getRiskEstimation());
        }
    }


    @Test
    void testRiskEstimation_Exception() {
        // Given
        String patientId = "12345";

        doReturn(mock(WebClient.RequestHeadersUriSpec.class)).when(webGateway).get();
        doThrow(new RuntimeException("Test exception")).when(webGateway).get();

        // When & Then
        assertThrows(ReportServiceException.RiskEstimationException.class, () -> reportService.riskEstimation(patientId));
    }
}
