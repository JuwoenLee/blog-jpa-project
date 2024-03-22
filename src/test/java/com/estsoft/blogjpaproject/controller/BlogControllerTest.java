package com.estsoft.blogjpaproject.controller;

import com.estsoft.blogjpaproject.domain.Article;
import com.estsoft.blogjpaproject.dto.AddArticleRequest;
import com.estsoft.blogjpaproject.repository.BlogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class BlogControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext ac;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    ObjectMapper objectMapper; // Object to json -> ObjectMapper

    @BeforeEach
    public void mockMvcSetUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ac).build();
    }

    @DisplayName("블로그 글 등록 성공")
    @Test
    public void addArticle() throws Exception {
//        given : 저장하고 싶은 블로그 정보
        AddArticleRequest request = new AddArticleRequest("제목", "내용");
        String json = objectMapper.writeValueAsString(request);

//        when : POST /api/articles
//        @RequestBody : json {"title" : "제목", "content" : "내용"}
        ResultActions resultActions = mockMvc.perform(post("/api/articles")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

//        then : Http Status code 201 검증,  저장이 잘 되었는지 확인 {"title" : "제목", "content" : "내용"}
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("title").value(request.getTitle()))
                .andExpect(jsonPath("content").value(request.getContent()));
    }

    @DisplayName("블로그 글 조회 성공")
    @Test
    public void showArticle() throws Exception {
//        given : blogRepository.save
        List<Article> articleList = new ArrayList<>();
        Article article1 = new Article("제목1", "내용1");
        Article article2 = new Article("제목2", "내용2");
        Article article3 = new Article("제목3", "내용3");

        articleList.add(article1);
        articleList.add(article2);
        articleList.add(article3);

        blogRepository.saveAll(articleList);

//        when : GET /api/articles
        ResultActions resultActions = mockMvc.perform(get("/api/articles"));

//        then : 호출 결과(Json)와 save한 데이터 결과 비교
//        [ {"title" : "제목1", "content" : "내용1"}, {"title" : "제목2", "content" : "내용2"}, {"title" : "제목3", "content" : "내용3"}]
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath(("$[0].title")).value(articleList.get(0).getTitle()))
                .andExpect(jsonPath("$[0].content").value(articleList.get(0).getContent()))
                .andExpect(jsonPath(("$[1].title")).value(articleList.get(1).getTitle()))
                .andExpect(jsonPath("$[1].content").value(articleList.get(1).getContent()))
                .andExpect(jsonPath(("$[2].title")).value(articleList.get(2).getTitle()))
                .andExpect(jsonPath("$[2].content").value(articleList.get(2).getContent()))
        ;
    }

    @DisplayName("블로그 글 삭제 성공")
    @Test
    public void deleteOneArticle() throws Exception {
//        given : 삭제할 데이터 만들기
        Article article = blogRepository.save(new Article("제목1", "내용1"));
        Long deleteId = article.getId();

//        when : DELETE /api/articles/{id}
        ResultActions resultActions = mockMvc.perform(delete("/api/articles/{id}", deleteId));

//        then : 삭제 결과 확인
        resultActions.andExpect(status().isOk());
        boolean isDeleted = blogRepository.findById(deleteId).isEmpty();
        Assertions.assertTrue(isDeleted);
    }

    @DisplayName("블로그 글 수정 성공")
    @Test
    public void updateOneArticle() throws Exception {
//        given : 수정할 데이터 만들기
        Article article = blogRepository.save(new Article("제목1", "내용1"));
        Long id = article.getId();
        AddArticleRequest request = new AddArticleRequest("제목", "내용");
        String json = objectMapper.writeValueAsString(request);

//        when : PUT /api/articles/{id}
        ResultActions resultActions = mockMvc.perform(put("/api/articles/{id}", id, request)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON_VALUE));

//        then : Http Status code 검증, 수정 전과 후 비교
        resultActions.andExpect(status().isOk());

        Article updatedArticle = blogRepository.findById(id).orElseThrow();

        boolean isSameTitle = article.getTitle().equals(updatedArticle.getTitle());
        boolean isSameContent = article.getContent().equals(updatedArticle.getContent());
        Assertions.assertFalse(isSameTitle && isSameContent);
    }

    // 코드 변경 사항 가정 (테스트 코드 삭제)
}