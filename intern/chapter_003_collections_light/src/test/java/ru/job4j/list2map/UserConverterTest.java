package ru.job4j.list2map;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConverterTest {

    @Test
    public void process() {
        List<User> input = List.of(
                new User(1, "Name1", "City1"),
                new User(2, "Name2", "City2"),
                new User(3, "Name3", "City3")
        );

        Map<Integer, User> expected = Map.of(
                1, new User(1, "Name1", "City1"),
                2, new User(2, "Name2", "City2"),
                3, new User(3, "Name3", "City3")
        );

        UserConverter userConverter = new UserConverter();
        Map<Integer, User> result = userConverter.process(input);

        assertThat(result, is(expected));
    }
}