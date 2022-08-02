package org.example.book.springboot.service;

import lombok.RequiredArgsConstructor;
import org.example.book.springboot.domain.posts.PostsRepository;
import org.example.book.springboot.web.dto.PostSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
