package ru.job4j.inheritance;

/**
 * 1. Реализация профессий в коде
 *
 * @author imoskovtsev
 */
public class Lesson {
    /**
     * Название урока.
     */
    private final String name;

    /**
     * Конструктор.
     *
     * @param name название урока
     */
    public Lesson(String name) {
        this.name = name;
    }

    /**
     * Возвращает название урока.
     *
     * @return название урока
     */
    public String getName() {
        return this.name;
    }
}
