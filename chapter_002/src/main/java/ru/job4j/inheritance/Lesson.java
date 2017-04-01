package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
class Lesson {
    /**
     * Название урока.
     */
    private String name;

    /**
     * Конструктор.
     * @param name название урока
     */
    Lesson(String name) {
        this.name = name;
    }

    /**
     * Возвращает название урока.
     * @return название урока
     */
    String getName() {
        return this.name;
    }
}
