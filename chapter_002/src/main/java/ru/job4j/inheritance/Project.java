package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
class Project {
    /**
     * Название проекта.
     */
    private String name;

    /**
     * Конструктор.
     * @param name название проекта
     */
    Project(String name) {
        this.name = name;
    }

    /**
     * Возвращает название проекта.
     * @return название проекта
     */
    String getName() {
        return this.name;
    }
}
