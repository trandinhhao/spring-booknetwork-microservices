package com.booknetwork.post.service;

import com.booknetwork.post.dto.request.PostRequest;
import com.booknetwork.post.dto.response.PageResponse;
import com.booknetwork.post.dto.response.PostResponse;
import com.booknetwork.post.dto.response.UserProfileResponse;
import com.booknetwork.post.entity.Post;
import com.booknetwork.post.mapper.PostMapper;
import com.booknetwork.post.repository.PostRepository;
import com.booknetwork.post.repository.httpclient.ProfileClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    DateTimeFormatter dateTimeFormatter;
    PostMapper postMapper;
    PostRepository postRepository;
    ProfileClient profileClient;

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

    public PageResponse<PostResponse> getMyPost(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        UserProfileResponse userProfile = null;

        try {
            userProfile= profileClient.getProfile(userId).getResult();
        } catch (Exception e) {

        }

        String username = userProfile.getUsername();
        Sort sort = Sort.by("createdDate").descending(); // sort theo field createdDate
        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = postRepository.findAllByUserId(userId, pageable);

        var postList = pageData.getContent().stream().map(post -> {
            var postResponse = postMapper.toPostResponse(post);
            postResponse.setCreated(dateTimeFormatter.format(post.getCreatedDate()));
            postResponse.setUsername(username);
            return postResponse;
        }).toList();

        return PageResponse.<PostResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(postList)
                .build();
    }
}
