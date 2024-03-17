package com.estsoft.blogjpaproject.controller;

import com.estsoft.blogjpaproject.dto.AddArticleRequest;
import com.estsoft.blogjpaproject.dto.ArticleResponse;
import com.estsoft.blogjpaproject.model.Article;
import com.estsoft.blogjpaproject.service.BlogService;
import com.estsoft.blogjpaproject.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {
    private final BlogService blogService;
    private final CommentService commentService;

    public BlogController(BlogService blogService, CommentService commentService) {
        this.blogService = blogService;
        this.commentService = commentService;
    }

    @PostMapping("/api/articles")   // json  { "title": "제목", "content": "내용"}
    public ResponseEntity<ArticleResponse> addArticle(@RequestBody AddArticleRequest request) {
        Article article = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(article.toResponse());    // json { "title": "제목", "content": "내용"}
    }

    @RequestMapping(value = "/api/articles", method = RequestMethod.GET)
    public ResponseEntity<List<ArticleResponse>> showArticle() {
        List<Article> articleList = blogService.findAll();
        List<ArticleResponse> responseList = articleList.stream()
                .map(ArticleResponse::new)
                .toList();
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> showOneArticle(@PathVariable Long id) {
        Article article = blogService.findById(id);   // 500 Error -> 404
        return ResponseEntity.ok(article.toResponse());
    }

    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteOneArticle(@PathVariable Long id) {
        commentService.deleteByArticleId(id); // 외래키 조건으로 인해 먼저 삭제
        blogService.deleteById(id); // 댓글 삭제 후 글 삭제
        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateOneArticle(@PathVariable Long id,
                                                    @RequestBody AddArticleRequest request) {
        Article updated = blogService.update(id, request);
        return ResponseEntity.ok(updated);
    }
}
