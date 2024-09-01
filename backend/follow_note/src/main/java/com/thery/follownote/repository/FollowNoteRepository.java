/*******************************************************************************
 * Repository interface for accessing Note documents in MongoDB.
 * This interface extends the MongoRepository interface, which provides
 * CRUD operations for the Note entity.
 ******************************************************************************/
package com.thery.follownote.repository;

import com.thery.follownote.document.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FollowNoteRepository extends MongoRepository<Note, String> {

    List<Note> findFollowNoteByPatientId(String patientId);
}