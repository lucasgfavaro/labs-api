package com.lgf.orderservice.model;

public record Order(
        String id,
        String userId,
        String product,
        Integer quantity
) {}

