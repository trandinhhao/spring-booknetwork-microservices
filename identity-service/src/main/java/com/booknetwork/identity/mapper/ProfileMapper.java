package com.booknetwork.identity.mapper;

import com.booknetwork.identity.dto.request.ProfileCreationRequest;
import com.booknetwork.identity.dto.request.UserCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
