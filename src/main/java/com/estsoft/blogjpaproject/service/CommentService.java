package com.estsoft.blogjpaproject.service;

import com.estsoft.blogjpaproject.dto.AddCommentRequest;
import com.estsoft.blogjpaproject.model.Article;
import com.estsoft.blogjpaproject.model.Comment;
import com.estsoft.blogjpaproject.repository.BlogRepository;
import com.estsoft.blogjpaproject.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private BlogRepository articleRepository;

    public CommentService(CommentRepository commentRepository, BlogRepository articleRepository) {
        this.commentRepository = commentRepository;
        this.articleRepository = articleRepository;
    }


    // 댓글 저장
    public Comment save(Long articleId, String body) {
        // articleId로 Article 엔티티를 찾습니다.
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 articleId에 해당하는 Article이 없습니다: " + articleId));

        // 댓글을 생성하고 article을 설정한 뒤 저장합니다.
        Comment comment = new Comment();
        comment.setArticle(article);
        comment.setBody(body);
        return commentRepository.save(comment);
    }

    public void deleteByArticleId(Long id) {
        commentRepository.deleteById(id);
    }

    public List<Comment> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    // 댓글 조회 (아이디와 게시물 아이디로)
    public Comment findByIdAndArticleId(Long commentId, Long articleId) {
        return commentRepository.findByIdAndArticleId(commentId, articleId);
    }
}
