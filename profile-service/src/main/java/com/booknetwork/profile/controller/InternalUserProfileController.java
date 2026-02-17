package com.booknetwork.profile.controller;

import com.booknetwork.profile.dto.ApiResponse;
import com.booknetwork.profile.dto.request.ProfileCreationRequest;
import com.booknetwork.profile.dto.response.UserProfileResponse;
import com.booknetwork.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {

    UserProfileService userProfileService;

    @PostMapping("/internal/users")
    ApiResponse<UserProfileResponse> createProfile(@RequestBody ProfileCreationRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.createProfile(request))
                .build();
    }

//    @GetMapping("/users/{profileId}")
//    public UserProfileResponse getProfile(@PathVariable String profileId) {
//        return userProfileService.getProfile(profileId);
//    }
}
