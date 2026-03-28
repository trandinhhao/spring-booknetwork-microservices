package com.booknetwork.chat.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "conversation")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParticipantInfo {
    String userId;
    String username;
    String firstName;
    String lastName;
    String avatar;
}
