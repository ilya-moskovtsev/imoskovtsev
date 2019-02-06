package ru.job4j.inheritance;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Проверить 1. Реализация профессий в коде
 *
 * @author imoskovtsev
 */
public class TeacherTest {
    /**
     * Учитель.
     */
    private static Teacher teacher;
    /**
     * Урок.
     */
    private static Lesson lesson;
    /**
     * Ученик.
     */
    private static Student student;

    /**
     * Подготовка к тестам.
     */
    @BeforeClass
    public static void beforeClass() {
        teacher = new Teacher("Педагогический", "Антон", "Москва", "495");
        lesson = new Lesson("математика");
        student = new Student("нет", "Владимир", "Москва", "495");
    }

    /**
     * Проверить Подготовить урок.
     */
    @Test
    public void prepareLesson() {
        String result = teacher.prepareLesson(lesson);
        String expected = "Учитель Антон подготавливает урок математика";
        assertThat(result, is(expected));
    }

    /**
     * Проверить Провести урок.
     */
    @Test
    public void giveLesson() {
        String result = teacher.giveLesson(lesson);
        String expected = "Учитель Антон проводит урок математика";
        assertThat(result, is(expected));
    }

    /**
     * Проверить Оценить прогресс ученика.
     */
    @Test
    public void assesStudentProgress() {
        String result = teacher.assesStudentProgress(student);
        String expected = "Учитель Антон оценивает прогресс ученика Владимир";
        assertThat(result, is(expected));
    }

}