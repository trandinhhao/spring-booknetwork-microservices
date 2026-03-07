package com.booknetwork.chat.repository;

import com.booknetwork.chat.entity.WebSocketSession;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WebSocketSessionRepository extends MongoRepository<WebSocketSession, String> {
    void deleteBySocketSessionId(String socketId);
    List<WebSocketSession> findAllByUserIdIn(List<String> userIds);
}
