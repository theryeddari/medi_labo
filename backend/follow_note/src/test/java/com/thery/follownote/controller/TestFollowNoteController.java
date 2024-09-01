package com.thery.follownote.controller;

import com.thery.follownote.dto.FollowNoteResponse;
import com.thery.follownote.dto.NoteRequest;
import com.thery.follownote.dto.NoteResponse;
import com.thery.follownote.exception.FollowNoteServiceException;
import com.thery.follownote.service.FollowNoteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestFollowNoteController {

    @InjectMocks
    private FollowNoteController followNoteController;

    @Mock
    private FollowNoteService followNoteService;

    @Test
    void getFollowNote_success() throws FollowNoteServiceException {
        FollowNoteResponse followNoteResponse = new FollowNoteResponse(List.of(new NoteResponse(LocalDateTime.now(), "surpoids")));

        when(followNoteService.findFollowNote("1")).thenReturn(followNoteResponse);

        FollowNoteResponse result = followNoteController.getFollowNote("1");

        verify(followNoteService).findFollowNote("1");
        assertEquals(followNoteResponse, result);
    }

    @Test
    void updateFollowNote_success() throws FollowNoteServiceException.SaveNoteException {
        NoteRequest noteRequest = new NoteRequest("1", LocalDateTime.now(), "Some note");
        NoteResponse noteResponse = new NoteResponse(LocalDateTime.now(), "Some note");
        when(followNoteService.saveFollowNote(noteRequest)).thenReturn(noteResponse);

        NoteResponse result = followNoteController.updateFollowNote(noteRequest);

        verify(followNoteService).saveFollowNote(noteRequest);
        assertEquals(noteResponse, result);
    }
}