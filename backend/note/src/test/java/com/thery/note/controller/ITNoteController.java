package com.thery.note.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thery.note.dto.NoteRequest;
import com.thery.note.dto.NoteResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Base64;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ITNoteController {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @AfterEach
    public void tearDown() {
        Query query = new Query();
        query.addCriteria(Criteria.where("patientId").regex("12345"));
        mongoTemplate.remove(query, "FollowNote");
    }

    @Test
    public void testGetNotes_Success() throws Exception {

        mockMvc.perform(get("/notes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("doctor:password".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(containsString("Poids")));


    }

    @Test
    public void testUpdateFollowNote_Success() throws Exception {

        NoteRequest noteRequest = new NoteRequest("12345", LocalDateTime.now(), "Some note");

        String jsonResponse = mockMvc.perform(post("/note")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(noteRequest))
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("doctor:password".getBytes())))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        NoteResponse noteResponse = objectMapper.readValue(jsonResponse, NoteResponse.class);

        Assertions.assertEquals(noteRequest.getNote(), noteResponse.getNote());
    }
}
