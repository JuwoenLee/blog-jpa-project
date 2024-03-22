package com.estsoft.blogjpaproject.dto;

import com.estsoft.blogjpaproject.domain.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponse {
    private Long id;
    private String body;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.body = comment.getBody();
        this.createdAt = comment.getCreatedAt();
    }
}