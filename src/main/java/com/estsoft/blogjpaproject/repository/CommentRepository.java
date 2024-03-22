package com.estsoft.blogjpaproject.repository;

import com.estsoft.blogjpaproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByIdAndArticleId(Long commentId, Long articleId);
    List<Comment> findByArticleId(Long articleId);
}
