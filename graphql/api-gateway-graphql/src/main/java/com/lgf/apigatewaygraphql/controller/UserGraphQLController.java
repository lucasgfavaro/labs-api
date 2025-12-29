package com.lgf.apigatewaygraphql.controller;

import com.lgf.apigatewaygraphql.client.OrdersClient;
import com.lgf.apigatewaygraphql.client.UsersClient;
import com.lgf.apigatewaygraphql.dto.OrderDto;
import com.lgf.apigatewaygraphql.dto.UserDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserGraphQLController {

    private final UsersClient usersClient;
    private final OrdersClient ordersClient;

    public UserGraphQLController(UsersClient usersClient, OrdersClient ordersClient) {
        this.usersClient = usersClient;
        this.ordersClient = ordersClient;
    }

    @QueryMapping
    public UserDto user(@Argument String id) {
        return usersClient.getUser(id);
    }

    @SchemaMapping(typeName = "User", field = "orders")
    public List<OrderDto> orders(UserDto user) {
        return ordersClient.getOrdersByUser(user.id());
    }
}

