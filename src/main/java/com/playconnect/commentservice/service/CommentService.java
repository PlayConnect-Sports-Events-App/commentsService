package com.playconnect.commentservice.service;

import com.playconnect.commentservice.dto.CommentRequest;
import com.playconnect.commentservice.dto.CommentResponse;
import com.playconnect.commentservice.model.Comment;
import com.playconnect.commentservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;

    public void createComment(CommentRequest commentRequest) {
        Comment comment = Comment.builder()
                .eventId(commentRequest.getEventId())
                .userId(commentRequest.getUserId())
                .content(commentRequest.getContent())
                .createdAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);
        log.info("Comment {} is saved", comment.getCommentId());
    }

    public List<CommentResponse> getCommentsForEvent(Long eventId) {
        List<Comment> comments = commentRepository.findByEventId(eventId);
        return comments.stream().map(this::mapToCommentResponse).toList();
    }

    private CommentResponse mapToCommentResponse(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getCommentId())
                .userId(comment.getUserId())
                .eventId(comment.getEventId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
        log.info("Comment {} is deleted", commentId);
    }
}
