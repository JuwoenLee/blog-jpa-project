package com.estsoft.blogjpaproject.dto;

import com.estsoft.blogjpaproject.model.Article;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleResponse {
    private String title;
    private String content;

    public ArticleResponse(Article article) {
        title = article.getTitle();
        content = article.getContent();
    }
}
