package com.estsoft.blogjpaproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA Auditing 기능 활성화 => 엔티티 변경 이력 추적
@EnableJpaAuditing // createdAt, updatedAt 자동 업데이트
@SpringBootApplication
public class BlogJpaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogJpaProjectApplication.class, args);
	}

}
