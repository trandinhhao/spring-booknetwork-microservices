package com.booknetwork.identity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.booknetwork.identity.dto.request.UserCreationRequest;
import com.booknetwork.identity.dto.request.UserUpdateRequest;
import com.booknetwork.identity.dto.response.UserResponse;
import com.booknetwork.identity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
