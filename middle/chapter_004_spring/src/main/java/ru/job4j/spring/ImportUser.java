package ru.job4j.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ImportUser implements Storage {
    private final Storage storage;

    @Autowired
    public ImportUser(@Qualifier(value = "jdbcStorage") Storage storage) {
        this.storage = storage;
    }

    @Override
    public void add(User user) {
        storage.add(user);
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        ImportUser importUser = context.getBean(ImportUser.class);
        importUser.add(new User());
    }
}
