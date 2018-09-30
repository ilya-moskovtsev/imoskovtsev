package ru.job4j.inheritance;

/**
 * 1. Реализация профессий в коде
 *
 * @author imoskovtsev
 */
public class Project {
    /**
     * Название проекта.
     */
    private final String name;

    /**
     * Конструктор.
     *
     * @param name название проекта
     */
    public Project(String name) {
        this.name = name;
    }

    /**
     * Возвращает название проекта.
     *
     * @return название проекта
     */
    public String getName() {
        return this.name;
    }
}
