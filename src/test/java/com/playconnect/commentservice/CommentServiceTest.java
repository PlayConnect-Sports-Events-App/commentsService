package com.playconnect.commentservice;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import com.playconnect.commentservice.dto.CommentRequest;
import com.playconnect.commentservice.dto.CommentResponse;
import com.playconnect.commentservice.model.Comment;
import com.playconnect.commentservice.repository.CommentRepository;
import com.playconnect.commentservice.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        commentService = new CommentService(commentRepository);  // Explicit constructor injection
    }

    @Test
    void testCreateComment() {
        CommentRequest commentRequest = new CommentRequest(1L, 2L, "Nice event!");
        Comment savedComment = new Comment(1L, 1L, 2L, "Nice event!", LocalDateTime.now());
        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);

        CommentResponse result = commentService.createComment(commentRequest);

        assertNotNull(result);
        assertEquals(commentRequest.getContent(), result.getContent());
        verify(commentRepository).save(any(Comment.class));
    }

    @Test
    void testGetCommentsForEvent() {
        List<Comment> comments = List.of(
                new Comment(1L, 1L, 2L, "Good job!", LocalDateTime.now()),
                new Comment(2L, 1L, 2L, "Well done!", LocalDateTime.now())
        );
        when(commentRepository.findByEventId(1L)).thenReturn(comments);

        List<CommentResponse> results = commentService.getCommentsForEvent(1L);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
        assertEquals("Good job!", results.get(0).getContent());
        verify(commentRepository).findByEventId(1L);
    }

    @Test
    void testDeleteComment() {
        doNothing().when(commentRepository).deleteById(anyLong());

        commentService.deleteComment(1L);

        verify(commentRepository).deleteById(1L);
    }
}
