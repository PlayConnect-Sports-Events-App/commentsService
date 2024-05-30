package com.playconnect.commentservice.controller;

import com.playconnect.commentservice.dto.CommentRequest;
import com.playconnect.commentservice.dto.CommentResponse;
import com.playconnect.commentservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createComment(@RequestBody CommentRequest commentRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(commentService.createComment(commentRequest));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{eventId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentResponse> getAllCommentsForEvent(@PathVariable Long eventId){
        return commentService.getCommentsForEvent(eventId);
    }

    @DeleteMapping("/{commentId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
