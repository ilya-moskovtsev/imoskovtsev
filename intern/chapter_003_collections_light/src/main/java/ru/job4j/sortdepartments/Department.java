package ru.job4j.sortdepartments;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Department {
    private List<String> subdivisions;

    public Department(String departmentName) {
        this.subdivisions = Arrays.asList(departmentName.split("\\\\"));
    }

    public List<String> getSubdivisions() {
        return subdivisions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Department that = (Department) o;
        return Objects.equals(subdivisions, that.subdivisions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subdivisions);
    }
}
