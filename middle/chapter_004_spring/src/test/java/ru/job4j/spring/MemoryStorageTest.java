package ru.job4j.spring;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class MemoryStorageTest {
    @Test
    public void whenSpringAppStartsThenBeansAreLoadedIntoContext() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        MemoryStorage memory = context.getBean(MemoryStorage.class);
        assertNotNull(memory);
        UserStorage storage = context.getBean(UserStorage.class);
        assertNotNull(storage);
        storage.add(new User());
    }
}