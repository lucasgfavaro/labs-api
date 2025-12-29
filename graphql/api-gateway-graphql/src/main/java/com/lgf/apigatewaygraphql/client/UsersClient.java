package com.lgf.apigatewaygraphql.client;

import com.lgf.apigatewaygraphql.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UsersClient {

    private final WebClient client;

    public UsersClient(WebClient.Builder builder, @Value("${user.service.base-url}") String baseUrl) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    public UserDto getUser(String id) {
        return client.get()
                .uri("/users/{id}", id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}
