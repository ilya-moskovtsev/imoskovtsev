package ru.job4j.groupstudents;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupStudents {
    /**
     * Returns a list of students who have a Grade Point Average more than bound.
     *
     * @param students list may contain null elements.
     * @param bound    required Grade Point Average.
     * @return a list of students who have a Grade Point Average more than bound.
     */
    List<Student> levelOf(List<Student> students, int bound) {
        return students.stream()
                .flatMap(Stream::ofNullable) // Remove null elements. Other option .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Student::getGpa).reversed().thenComparing(Student::getName))
                .takeWhile(student -> student.getGpa() > bound)
                .collect(Collectors.toList());
    }
}
