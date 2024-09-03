/**
 * Enumeration representing various risk words associated with medical reports.
 */
package com.thery.report.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum RiskWord {
    HEMOGLOBINE(List.of("Hémoglobine A1C")),
    MICROALBUMINE(List.of("Microalbumine")),
    TAILLE(List.of("Taille")),
    POIDS(List.of("Poids")),
    FUMEUR(List.of("Fumeur", "Fumeuse")),
    ANORMAL(List.of("Anormal")),
    CHOLESTEROL(List.of("Cholestérol")),
    VERTIGES(List.of("Vertiges")),
    RECHUTE(List.of("Rechute")),
    REACTION(List.of("Réaction")),
    ANTICORPS(List.of("Anticorps"));

    private final List<String> terms;
}