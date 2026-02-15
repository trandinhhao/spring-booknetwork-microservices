package com.booknetwork.profile.mapper;

import com.booknetwork.profile.dto.request.ProfileCreationRequest;
import com.booknetwork.profile.dto.response.UserProfileResponse;
import com.booknetwork.profile.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(ProfileCreationRequest request);
    UserProfileResponse toUserProfileResponse(UserProfile entity);
}
