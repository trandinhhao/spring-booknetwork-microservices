package com.booknetwork.chat.repository.httpclient;

import com.booknetwork.chat.dto.ApiResponse;
import com.booknetwork.chat.dto.request.IntrospectRequest;
import com.booknetwork.chat.dto.response.IntrospectResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "identity-client", url = "${app.services.identity.url}")
public interface IdentityClient {
    @PostMapping("/auth/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request);
}
