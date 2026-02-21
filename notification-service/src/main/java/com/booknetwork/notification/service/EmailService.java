package com.booknetwork.notification.service;

import com.booknetwork.notification.dto.request.EmailRequest;
import com.booknetwork.notification.dto.request.SendEmailRequest;
import com.booknetwork.notification.dto.request.Sender;
import com.booknetwork.notification.dto.response.EmailResponse;
import com.booknetwork.notification.exception.AppException;
import com.booknetwork.notification.exception.ErrorCode;
import com.booknetwork.notification.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {
    EmailClient emailClient;

    @Value("${}")
    String apikey = ;

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("Hao Tran")
                        .email("haodinhtran06@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();

        try {
            return emailClient.sendEmail(apikey, emailRequest);
        } catch (FeignException e) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
