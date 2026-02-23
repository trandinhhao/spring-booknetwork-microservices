package com.booknetwork.post.service;

import com.booknetwork.post.dto.request.PostRequest;
import com.booknetwork.post.dto.response.PostResponse;
import com.booknetwork.post.entity.Post;
import com.booknetwork.post.mapper.PostMapper;
import com.booknetwork.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {

    PostMapper postMapper;
    PostRepository postRepository;

    public PostResponse createPost(PostRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Post post = Post.builder()
                .content(request.getContent())
                .userId(authentication.getName())
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .build();

        postRepository.save(post);
        return postMapper.toPostResponse(post);
    }

    public List<PostResponse> getMyPost() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        return postRepository.findAllByUserId(userId)
                .stream()
                .map(postMapper::toPostResponse)
                .toList();
    }
}
