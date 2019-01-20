package groupstudents;

import java.util.Objects;

public class Student {
    private static final String STUDENT_TO_STRING = "Student{name='%s', gpa=%d}";
    private String name;
    private int gpa; // Grade Point Average

    public Student(String name, int gpa) {
        this.name = name;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public int getGpa() {
        return gpa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return gpa == student.gpa
                && Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gpa);
    }

    @Override
    public String toString() {
        return String.format(STUDENT_TO_STRING, name, gpa);
    }
}
