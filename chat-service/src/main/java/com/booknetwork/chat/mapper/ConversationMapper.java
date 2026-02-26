package com.booknetwork.chat.mapper;

import com.booknetwork.chat.dto.response.ConversationResponse;
import com.booknetwork.chat.entity.Conversation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    ConversationResponse toConversationResponse(Conversation conversation);

    List<ConversationResponse> toConversationResponseList(List<Conversation> conversations);
}
