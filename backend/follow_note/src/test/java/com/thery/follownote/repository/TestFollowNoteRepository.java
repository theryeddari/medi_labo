package com.thery.follownote.repository;

import com.thery.follownote.document.Note;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestFollowNoteRepository {

    @Autowired
    private FollowNoteRepository followNoteRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    public void tearDown() {
        Query query = new Query();
        query.addCriteria(Criteria.where("patientId").regex("12345"));
        mongoTemplate.remove(query, "FollowNote");
    }

    @Test
    public void testNoteCreation() {
        Note note = new Note();
        note.setPatientId("12345");
        note.setDate(LocalDateTime.now());
        note.setNote("Initial note");
        note.setPatient("John Doe");

        note = followNoteRepository.save(note);
        assertNotNull(note.getId());
        assertEquals("12345", note.getPatientId());
        assertEquals("Initial note", note.getNote());
    }

    @Test
    public void testNoteUpdate() {
        Note note = new Note();
        note.setPatientId("12345");
        note.setDate(LocalDateTime.now());
        note.setNote("Initial note");
        note.setPatient("John Doe");

        note = followNoteRepository.save(note);

        note.setNote("Updated note");
        followNoteRepository.save(note);

        Note updatedNote = followNoteRepository.findById(note.getId()).orElse(null);
        assertNotNull(updatedNote);
        assertEquals("Updated note", updatedNote.getNote());
    }

    @Test
    public void testFindFollowNoteByPatientId() {
        Note note1 = new Note();
        note1.setPatientId("12345");
        note1.setDate(LocalDateTime.now());
        note1.setNote("First note");
        note1.setPatient("John Doe");
        followNoteRepository.save(note1);

        Note note2 = new Note();
        note2.setPatientId("12345");
        note2.setDate(LocalDateTime.now().plusDays(1));
        note2.setNote("Second note");
        note2.setPatient("John Doe");
        followNoteRepository.save(note2);

        List<Note> notes = followNoteRepository.findFollowNoteByPatientId("12345");
        assertFalse(notes.isEmpty());
        assertEquals(2, notes.size());
        assertTrue(notes.stream().anyMatch(n -> "First note".equals(n.getNote())));
        assertTrue(notes.stream().anyMatch(n -> "Second note".equals(n.getNote())));
    }

    @Test
    public void testDeleteNote() {
        Note note = new Note();
        note.setPatientId("12345");
        note.setDate(LocalDateTime.now());
        note.setNote("Note to be deleted");
        note.setPatient("John Doe");
        note = followNoteRepository.save(note);

        String noteId = note.getId();
        followNoteRepository.delete(note);

        Optional<Note> deletedNote = followNoteRepository.findById(noteId);
        assertFalse(deletedNote.isPresent());
    }
}

