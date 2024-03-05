package com.playconnect.commentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long commentId;
    private Long userId; // The ID of the user who created the comment
    private Long eventId; // The ID of the event the comment is related to
    private String content;
    private LocalDateTime createdAt;
}
