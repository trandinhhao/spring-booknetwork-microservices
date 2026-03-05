package com.booknetwork.chat.mapper;


import com.booknetwork.chat.dto.request.ChatMessageRequest;
import com.booknetwork.chat.dto.response.ChatMessageResponse;
import com.booknetwork.chat.entity.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageResponse toChatMessageResponse(ChatMessage chatMessage);

    ChatMessage toChatMessage(ChatMessageRequest request);

    List<ChatMessageResponse> toChatMessageResponses(List<ChatMessage> chatMessages);
}