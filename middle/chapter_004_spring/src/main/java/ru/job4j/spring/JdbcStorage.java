package ru.job4j.spring;

import org.springframework.stereotype.Component;

@Component
public class JdbcStorage implements Storage {
    @Override
    public void add(User user) {
        System.out.println("store to db");
    }
}
