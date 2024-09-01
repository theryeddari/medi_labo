/*******************************************************************************
 * Repository interface for accessing Note documents in MongoDB.
 * This interface extends the MongoRepository interface, which provides
 * CRUD operations for the Note entity.
 ******************************************************************************/
package com.thery.note.repository;

import com.thery.note.document.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note, String> {

    List<Note> findFollowNoteByPatientId(String patientId);
}