package ru.job4j.sortdepartments;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortedDepartmentsTest {

    @Test
    public void getAscendingDepartments() {
        SortedDepartments sortedDepartments = new SortedDepartments();

        List<String> input = List.of(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );

        List<Department> expected = List.of(
                new Department("K1"),
                new Department("K1\\SK1"),
                new Department("K1\\SK1\\SSK1"),
                new Department("K1\\SK1\\SSK2"),
                new Department("K1\\SK2"),
                new Department("K2"),
                new Department("K2\\SK1"),
                new Department("K2\\SK1\\SSK1"),
                new Department("K2\\SK1\\SSK2")
        );

        input.forEach(sortedDepartments::addDepartment);

        List<Department> result = sortedDepartments.getAscendingDepartments();

        assertThat(result, is(expected));
    }

    @Test
    public void getDescendingDepartments() {
        SortedDepartments sortedDepartments = new SortedDepartments();

        List<String> input = List.of(
                "K1\\SK1",
                "K1\\SK2",
                "K1\\SK1\\SSK1",
                "K1\\SK1\\SSK2",
                "K2",
                "K2\\SK1\\SSK1",
                "K2\\SK1\\SSK2"
        );

        List<Department> expected = List.of(
                new Department("K2"),
                new Department("K2\\SK1"),
                new Department("K2\\SK1\\SSK2"),
                new Department("K2\\SK1\\SSK1"),
                new Department("K1"),
                new Department("K1\\SK2"),
                new Department("K1\\SK1"),
                new Department("K1\\SK1\\SSK2"),
                new Department("K1\\SK1\\SSK1")
        );

        input.forEach(sortedDepartments::addDepartment);

        List<Department> result = sortedDepartments.getDescendingDepartments();

        assertThat(result, is(expected));
    }
}