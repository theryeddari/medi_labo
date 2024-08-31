/**
 * The entry point for the Patient application.
 * This class starts the Spring Boot application.
 */
package com.thery.patient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientApplication {

    /**
     * Main method that launches the Patient application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }

}