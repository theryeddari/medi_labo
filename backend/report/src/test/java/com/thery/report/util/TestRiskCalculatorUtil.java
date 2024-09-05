package com.thery.report.util;

import com.thery.report.dto.NoteResponse;
import com.thery.report.dto.NotesResponse;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.thery.report.util.RiskCalculatorUtil.calculateRisk;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestRiskCalculatorUtil {

    @Test
    void testCalculateRisk_None() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "Le patient est en bonne santé."),
                new NoteResponse(LocalDateTime.now(), "Aucun problème significatif.")
        ));

        String risk = calculateRisk(40, true, notesResponse);
        assertEquals("None", risk);
    }

    @Test
    void testCalculateRisk_Borderline() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "Le patient a un taux élevé de cholestérol"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des problèmes de Poids")
        ));

        String risk = calculateRisk(35, true, notesResponse);
        assertEquals("Borderline", risk);
    }

    @Test
    void testCalculateRisk_Danger_YoungMan() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "Le patient est fumeur"),
                new NoteResponse(LocalDateTime.now(), "Le patient a un taux élevé d' hémoglobine A1C"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des vertiges")
        ));

        String risk = calculateRisk(25, true, notesResponse);
        assertEquals("Danger", risk);
    }

    @Test
    void testCalculateRisk_Danger_YoungWoman() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "La patiente est fumeuse"),
                new NoteResponse(LocalDateTime.now(), "La patiente a fait une rechute"),
                new NoteResponse(LocalDateTime.now(), "La patiente a un taux élevé de cholestérol"),
                new NoteResponse(LocalDateTime.now(), "La patiente a une réaction au traitement")
        ));

        String risk = calculateRisk(25, false, notesResponse);
        assertEquals("Danger", risk);
    }

    @Test
    void testCalculateRisk_Danger_Older() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "Le patient est fumeur"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des antécédents de rechute"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des anticorps présents"),
                new NoteResponse(LocalDateTime.now(), "Le patient a un résultat anormal"),
                new NoteResponse(LocalDateTime.now(), "Le patient a une Microalbumine"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des vertiges")
        ));

        String risk = calculateRisk(45, true, notesResponse);
        assertEquals("Danger", risk);
    }

    @Test
    void testCalculateRisk_EarlyOnset_YoungMan() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "Le patient est fumeur"),
                new NoteResponse(LocalDateTime.now(), "Le patient a un taux élevé d'hémoglobine A1C"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des vertiges"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des anticorps présents"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des problèmes de poids")
        ));

        String risk = calculateRisk(25, true, notesResponse);
        assertEquals("Early onset", risk);
    }

    @Test
    void testCalculateRisk_EarlyOnset_YoungWoman() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "La patiente est fumeuse"),
                new NoteResponse(LocalDateTime.now(), "La patiente a fait une rechute"),
                new NoteResponse(LocalDateTime.now(), "La patiente a un taux élevé de cholestérol"),
                new NoteResponse(LocalDateTime.now(), "La patiente a une réaction au traitement"),
                new NoteResponse(LocalDateTime.now(), "La patiente a des anticorps présents"),
                new NoteResponse(LocalDateTime.now(), "La patiente a un resultat anormal"),
                new NoteResponse(LocalDateTime.now(), "La patiente a une microalbumine")
        ));

        String risk = calculateRisk(25, false, notesResponse);
        assertEquals("Early onset", risk);
    }

    @Test
    void testCalculateRisk_EarlyOnset_Older() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "Le patient est fumeur"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des antécédents de rechute"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des anticorps présents"),
                new NoteResponse(LocalDateTime.now(), "Le patient a un résultat anormal"),
                new NoteResponse(LocalDateTime.now(), "Le patient a une microalbumine"),
                new NoteResponse(LocalDateTime.now(), "Le patient a des vertiges"),
                new NoteResponse(LocalDateTime.now(), "Le patient a une réaction au traitement"),
                new NoteResponse(LocalDateTime.now(), "Le patient a un taux élevé de cholestérol")
        ));

        String risk = calculateRisk(50, true, notesResponse);
        assertEquals("Early onset", risk);
    }

    @Test
    void testCalculateRisk_NotAvailable() {
        NotesResponse notesResponse = new NotesResponse(List.of(
                new NoteResponse(LocalDateTime.now(), "Le patient présente un resultat anormal")
        ));

        String risk = calculateRisk(28, true, notesResponse);
        assertEquals("Not available", risk);
    }
}
