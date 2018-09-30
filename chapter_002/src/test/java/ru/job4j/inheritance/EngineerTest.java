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
public class EngineerTest {
    /**
     * Инженер.
     */
    private static Engineer engineer;
    /**
     * Данные.
     */
    private static MyData myData;
    /**
     * Проект.
     */
    private static Project project;

    /**
     * Подготовка к тестам.
     */
    @BeforeClass
    public static void beforeClass() {
        engineer = new Engineer("Инженерный", "Сергей", "Москва", "495");
        myData = new MyData("чертеж");
        project = new Project("секретный");
    }

    /**
     * Проверить Исследовать данные.
     */
    @Test
    public void research() {
        String result = engineer.research(myData);
        String expected = "Инженер Сергей исследует данные чертеж";
        assertThat(result, is(expected));
    }

    /**
     * Проверить Проектировать данные.
     */
    @Test
    public void design() {
        String result = engineer.design(myData);
        String expected = "Инженер Сергей проектирует данные чертеж";
        assertThat(result, is(expected));
    }

    /**
     * Проверить Разрабатывать проект.
     */
    @Test
    public void develop() {
        String result = engineer.develop(project);
        String expected = "Инженер Сергей разрабатывает проект секретный";
        assertThat(result, is(expected));
    }

    /**
     * Проверить Тестировать проект.
     */
    @Test
    public void test() {
        String result = engineer.test(project);
        String expected = "Инженер Сергей тестирует проект секретный";
        assertThat(result, is(expected));
    }
}