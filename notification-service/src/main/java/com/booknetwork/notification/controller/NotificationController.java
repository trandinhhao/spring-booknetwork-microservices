package com.booknetwork.notification.controller;

import com.booknetwork.event.dto.NotificationEvent;
import com.booknetwork.notification.dto.request.Recipient;
import com.booknetwork.notification.dto.request.SendEmailRequest;
import com.booknetwork.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NotificationController {

    EmailService emailService;

    @KafkaListener(topics = "notification-delivery")
    public void listenNotificationDelivery(NotificationEvent message) {
        emailService.sendEmail(
                SendEmailRequest.builder()
                        .to(Recipient.builder()
                                .email(message.getRecipient())
                                .build())
                        .subject(message.getSubject())
                        .htmlContent(message.getBody())
                        .build()
        );
    }

}
