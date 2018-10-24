package ru.job4j.sortuser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void sort() {
        List<User> input = new ArrayList<>(Arrays.asList(
                new User("name1", 40),
                new User("name2", 30),
                new User("name3", 50)
        ));
        Set<User> expected = new TreeSet<>(Arrays.asList(
                new User("name2", 30),
                new User("name1", 40),
                new User("name3", 50)
        ));

        Set<User> result = new SortUser().sort(input);
        assertThat(result, is(expected));
    }
}