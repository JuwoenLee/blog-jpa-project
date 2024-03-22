package com.estsoft.blogjpaproject.dto;

import com.estsoft.blogjpaproject.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentRequest {
    private String body;

    public Comment toEntity() {	// 생성자를 사용해 객체 생성
        return Comment.builder()
                .body(body)
                .build();
    }
}
