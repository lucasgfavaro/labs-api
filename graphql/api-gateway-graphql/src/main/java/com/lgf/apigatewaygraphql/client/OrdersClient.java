package com.lgf.apigatewaygraphql.client;

import com.lgf.apigatewaygraphql.dto.OrderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class OrdersClient {

    private final WebClient client;

    public OrdersClient(WebClient.Builder builder, @Value("${order.service.base-url}") String baseUrl) {
        this.client = builder.baseUrl(baseUrl).build();
    }

    public List<OrderDto> getOrdersByUser(String userId) {
        return client.get()
                .uri(uri -> uri.path("/orders")
                        .queryParam("userId", userId)
                        .build())
                .retrieve()
                .bodyToFlux(OrderDto.class)
                .collectList()
                .block();
    }
}

