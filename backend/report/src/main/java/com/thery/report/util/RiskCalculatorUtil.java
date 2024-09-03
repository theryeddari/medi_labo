/*******************************************************************************
 * Utility class for calculating risk based on age, gender, and notes response.
 ******************************************************************************/
package com.thery.report.util;

import com.thery.report.constant.RiskWord;
import com.thery.report.dto.NotesResponse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * RiskCalculatorUtil provides methods to calculate risk levels and ages.
 */
public class RiskCalculatorUtil {

    /**
     * Calculates the risk level based on age, gender, and notes response.
     *
     * @param age           the age of the individual
     * @param isMan         true if the individual is male, false otherwise
     * @param notesResponse the notes response containing risk-related terms
     * @return the risk level as a String
     */
    public static String calculateRisk(int age, boolean isMan, NotesResponse notesResponse) {
        int matchRiskCount = notesResponse.getNoteResponseList().stream()
                .flatMap(note -> Arrays.stream(RiskWord.values())
                        .flatMap(riskWord -> riskWord.getTerms().stream())
                        .filter(term -> note.getNote().toLowerCase().contains(term.toLowerCase())))
                .collect(Collectors.toSet())
                .size();

        if (matchRiskCount == 0) {
            return "None";
        } else if (age > 30 && matchRiskCount >= 2 && matchRiskCount <= 5) {
            return "Borderline";
        } else if (age < 30 && isMan && matchRiskCount >= 3 && matchRiskCount <= 4) {
            return "Danger";
        } else if (age < 30 && !isMan && matchRiskCount >= 4 && matchRiskCount <= 6) {
            return "Danger";
        } else if (age > 30 && (matchRiskCount == 7 || matchRiskCount == 6)) {
            return "Danger";
        } else if (age < 30 && isMan && matchRiskCount >= 5) {
            return "Early onset";
        } else if (age < 30 && !isMan && matchRiskCount >= 7) {
            return "Early onset";
        } else if (age > 30 && matchRiskCount >= 8) {
            return "Early onset";
        } else {
            return "Not available";
        }
    }

    /**
     * Calculates the age based on the provided birthdate.
     *
     * @param birthdate the birthdate of the individual
     * @return the calculated age as an integer
     */
    public static int calculateAge(LocalDateTime birthdate) {
        return Math.abs(birthdate.minusYears(LocalDateTime.now().getYear()).getYear());
    }
}