package com.example.post.config;

import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class SeedData implements CommandLineRunner {

    private final PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        // 데이터가 이미 존재하지 않는 경우에만 초기 데이터 삽입
        if (postRepository.count() == 0) {
            // 데이터 생성 및 저장 (한글 데이터를 포함하여 삽입)
            postRepository.save(new Post(null, "첫 번째 게시물", "이것은 첫 번째 게시물의 내용입니다.", LocalDateTime.of(2024, 10, 1, 10, 0), LocalDateTime.of(2024, 10, 1, 10, 0)));
            postRepository.save(new Post(null, "두 번째 게시물", "이것은 두 번째 게시물의 내용입니다.", LocalDateTime.of(2024, 10, 1, 11, 0), LocalDateTime.of(2024, 10, 1, 11, 0)));
            postRepository.save(new Post(null, "세 번째 게시물", "이것은 세 번째 게시물의 내용입니다.", LocalDateTime.of(2024, 10, 1, 12, 0), LocalDateTime.of(2024, 10, 1, 12, 0)));
            postRepository.save(new Post(null, "네 번째 게시물", "이것은 네 번째 게시물의 내용입니다.", LocalDateTime.of(2024, 10, 1, 13, 0), LocalDateTime.of(2024, 10, 1, 13, 0)));
            postRepository.save(new Post(null, "다섯 번째 게시물", "이것은 다섯 번째 게시물의 내용입니다.", LocalDateTime.of(2024, 10, 1, 14, 0), LocalDateTime.of(2024, 10, 1, 14, 0)));
            postRepository.save(new Post(null, "여섯 번째 게시물", "이것은 여섯 번째 게시물의 내용입니다.", LocalDateTime.of(2024, 10, 1, 15, 0), LocalDateTime.of(2024, 10, 1, 15, 0)));
            postRepository.save(new Post(null, "일곱 번째 게시물", "이것은 일곱 번째 게시물의 내용입니다.", LocalDateTime.of(2024, 10, 1, 16, 0), LocalDateTime.of(2024, 10, 1, 16, 0)));
        }
    }
}
