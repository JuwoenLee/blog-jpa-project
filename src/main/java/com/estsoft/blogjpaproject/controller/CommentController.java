package com.estsoft.blogjpaproject.controller;

import com.estsoft.blogjpaproject.dto.AddCommentRequest;
import com.estsoft.blogjpaproject.dto.CommentResponse;
import com.estsoft.blogjpaproject.domain.Comment;
import com.estsoft.blogjpaproject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments/{articleId}")
    public ResponseEntity<CommentResponse> addComment(@RequestBody AddCommentRequest request, @PathVariable Long articleId) {
        Comment comment = commentService.save(articleId, request.getBody());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(comment.toResponse());
    }

    @GetMapping("/comments/{articleId}")
    public ResponseEntity<List<CommentResponse>> getCommentsByArticleId(@PathVariable Long articleId) {
        List<Comment> comments = commentService.findByArticleId(articleId);
        List<CommentResponse> commentResponses = comments.stream()
                .map(Comment::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentResponses);
    }

    @GetMapping("/comments/{articleId}/{commentId}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable Long articleId, @PathVariable Long commentId) {
        Optional<Comment> optionalComment = Optional.ofNullable(commentService.findByIdAndArticleId(commentId, articleId));
        if (optionalComment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Comment comment = optionalComment.get();
        return ResponseEntity.ok(CommentResponse.builder()
                .id(comment.getId())
                .body(comment.getBody())
                .createdAt(comment.getCreatedAt())
                .build());
    }

}
