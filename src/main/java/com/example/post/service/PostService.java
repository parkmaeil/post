package com.example.post.service;

import com.example.post.entity.Post;
import com.example.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public Page<Post> findAll(int offset, int pageSize, String field){
        return postRepository
                .findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.DESC, field));
    }
}
