package com.lgf.orderservice.controller;

import com.lgf.orderservice.model.Order;
import com.lgf.orderservice.repository.InMemoryOrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final InMemoryOrderRepository repo;

    public OrderController(InMemoryOrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public List<Order> getAll() {
        return repo.findAll();
    }

    @GetMapping
    public List<Order> getByUser(@RequestParam String userId) {
        return repo.findByUserId(userId);
    }
}

