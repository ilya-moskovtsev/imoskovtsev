package ru.job4j.list2map;

import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConverterTest {

    @Test
    public void process() {
        List<User> input = new ArrayList<>();
        input.add(new User(1, "Name1", "City1"));
        input.add(new User(2, "Name2", "City2"));
        input.add(new User(3, "Name3", "City3"));

        Map<Integer, User> expected = new HashMap<>();
        expected.put(1, new User(1, "Name1", "City1"));
        expected.put(2, new User(2, "Name2", "City2"));
        expected.put(3, new User(3, "Name3", "City3"));

        UserConverter userConverter = new UserConverter();
        Map<Integer, User> result = userConverter.process(input);

        assertThat(result, is(expected));
    }
}