package ru.job4j.sortuser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void sort() {
        List<User> input = List.of(
                new User("name1", 40),
                new User("name2", 30),
                new User("name3", 50)
        );
        Set<User> expected = Set.of(
                new User("name2", 30),
                new User("name1", 40),
                new User("name3", 50)
        );

        Set<User> result = new SortUser().sort(input);
        assertThat(result, is(expected));
    }

    @Test
    public void sortByNameLength() {
        List<User> input = Arrays.asList(
                new User("ab", 21),
                new User("abcd", 21),
                new User("abc", 21),
                new User("a", 21)
        );
        List<User> expected = List.of(
                new User("a", 21),
                new User("ab", 21),
                new User("abc", 21),
                new User("abcd", 21)
        );

        List<User> result = new SortUser().sortByNameLength(input);
        assertThat(result, is(expected));
    }

    @Test
    public void sortByAllFields() {
        List<User> input = Arrays.asList(
                new User("xyz", 25),
                new User("abc", 30),
                new User("xyz", 20),
                new User("abc", 25)
        );
        List<User> expected = List.of(
                new User("abc", 25),
                new User("abc", 30),
                new User("xyz", 20),
                new User("xyz", 25)
        );

        List<User> result = new SortUser().sortByAllFields(input);
        assertThat(result, is(expected));
    }
}