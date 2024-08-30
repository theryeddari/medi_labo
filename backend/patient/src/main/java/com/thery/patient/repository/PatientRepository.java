package com.thery.patient.repository;

import com.thery.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for the Patient entity, providing CRUD operations.
 * <p>
 * This interface extends JpaRepository, which provides methods for interacting with the BidList entity.
 * <p>
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

}
