package ru.job4j.sortdepartments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class SortedDepartments {

    private TreeSet<String> departmentNames = new TreeSet<>();

    public void addDepartment(String departmentName) {
        List<String> subdivisions = Arrays.asList(departmentName.split("\\\\"));
        IntStream.range(0, subdivisions.size()).forEach(i -> this.departmentNames.add(String.join("\\", subdivisions.subList(0, i + 1))));
    }

    public List<Department> getAscendingDepartments() {
        List<Department> departments = new ArrayList<>();
        this.departmentNames.forEach(department -> departments.add(new Department(department)));
        AscendingComparator comparator = new AscendingComparator();
        departments.sort(comparator);

        return departments;
    }

    public List<Department> getDescendingDepartments() {
        List<Department> departments = new ArrayList<>();
        this.departmentNames.forEach(department -> departments.add(new Department(department)));
        DescendingComparator comparator = new DescendingComparator();
        departments.sort(comparator);

        return departments;
    }
}
