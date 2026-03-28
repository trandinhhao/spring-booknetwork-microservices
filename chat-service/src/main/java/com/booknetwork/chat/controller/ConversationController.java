package com.booknetwork.chat.controller;

import com.booknetwork.chat.dto.ApiResponse;
import com.booknetwork.chat.dto.request.ConversationRequest;
import com.booknetwork.chat.dto.response.ConversationResponse;
import com.booknetwork.chat.service.ConversationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("conversations")
public class ConversationController {
    ConversationService conversationService;

    @PostMapping("/create")
    ApiResponse<ConversationResponse> createConversation(@RequestBody @Valid ConversationRequest request) {
        return ApiResponse.<ConversationResponse>builder()
                .result(conversationService.create(request))
                .build();
    }

    @GetMapping("/my-conversations")
    ApiResponse<List<ConversationResponse>> myConversations() {
        return ApiResponse.<List<ConversationResponse>>builder()
                .result(conversationService.myConversations())
                .build();
    }

}
