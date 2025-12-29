package com.lgf.userservice.repository.dto;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryUserRepository {

    private final Map<String, User> users = new ConcurrentHashMap<>();

    @PostConstruct
    void init() {
        save(new User("1", "Lucas", "lucas@mail.com"));
        save(new User("2", "Ana", "ana@mail.com"));
        save(new User("3", "Juan", "juan@mail.com"));
    }

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User findById(String id) {
        return users.get(id);
    }

    public User save(User user) {
        users.put(user.id(), user);
        return user;
    }
}
