package com.lgf.orderservice.repository;

import com.lgf.orderservice.model.Order;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryOrderRepository {

    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        save(new Order("o1", "1", "Laptop", 1));
        save(new Order("o2", "1", "Mouse", 2));
        save(new Order("o3", "2", "Keyboard", 1));
    }

    public List<Order> findByUserId(String userId) {
        return orders.values()
                .stream()
                .filter(o -> o.userId().equals(userId))
                .toList();
    }

    public List<Order> findAll() {
        return List.copyOf(orders.values());
    }

    public Order save(Order order) {
        orders.put(order.id(), order);
        return order;
    }
}

