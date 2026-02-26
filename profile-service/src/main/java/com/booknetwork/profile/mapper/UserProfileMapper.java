package com.booknetwork.profile.mapper;

import com.booknetwork.profile.dto.request.ProfileCreationRequest;
import com.booknetwork.profile.dto.request.UpdateProfileRequest;
import com.booknetwork.profile.dto.response.UserProfileResponse;
import com.booknetwork.profile.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);
    UserProfileResponse toUserProfileResponse(UserProfile entity);
    void update(@MappingTarget UserProfile entity, UpdateProfileRequest request);
}
