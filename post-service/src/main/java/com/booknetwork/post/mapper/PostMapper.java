package com.booknetwork.post.mapper;

import com.booknetwork.post.dto.response.PostResponse;
import com.booknetwork.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
