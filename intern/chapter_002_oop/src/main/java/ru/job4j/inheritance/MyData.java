package ru.job4j.inheritance;

/**
 * 1. Реализация профессий в коде
 *
 * @author imoskovtsev
 */
public class MyData {
    /**
     * Тип данных.
     */
    private final String type;

    /**
     * Конструктор.
     *
     * @param type тип данных
     */
    public MyData(String type) {
        this.type = type;
    }

    /**
     * Возвращает тип данных.
     *
     * @return тип данных
     */
    public String getType() {
        return this.type;
    }
}
