package ru.job4j.list;

import org.junit.Test;

import java.util.List;

import ru.job4j.list.Analyze.User;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {

    @Test
    public void diffAdded() {
        var previous = List.of(
                new User(1, "name1"),
                new User(3, "name3")
        );
        var current = List.of(
                new User(1, "name1"),
                new User(2, "name2"),
                new User(3, "name3")
        );

        var analyze = new Analyze();
        var info = analyze.diff(previous, current);

        assertThat(info.added, is(1));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void diffChanged() {
        var previous = List.of(
                new User(1, "name1"),
                new User(2, "name2")
        );
        var current = List.of(
                new User(1, "updated name"),
                new User(2, "name2")
        );

        var analyze = new Analyze();
        var info = analyze.diff(previous, current);

        assertThat(info.added, is(0));
        assertThat(info.changed, is(1));
        assertThat(info.deleted, is(0));
    }

    @Test
    public void diffDeleted() {
        var previous = List.of(
                new User(1, "name1"),
                new User(2, "name2")
        );
        var current = List.of(
                new User(1, "name1")
        );

        var analyze = new Analyze();
        var info = analyze.diff(previous, current);

        assertThat(info.added, is(0));
        assertThat(info.changed, is(0));
        assertThat(info.deleted, is(1));
    }
}