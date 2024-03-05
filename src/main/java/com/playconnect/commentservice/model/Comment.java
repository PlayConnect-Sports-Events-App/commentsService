package com.playconnect.commentservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private Long userId; // The ID of the user who created the comment
    private Long eventId; // The ID of the event the comment is related to
    private String content;
    private LocalDateTime createdAt;
}
