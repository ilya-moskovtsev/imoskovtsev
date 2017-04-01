package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
class MyData {
    /**
     * Тип данных.
     */
    private String type;

    /**
     * Конструктор.
     * @param type тип данных
     */
    MyData(String type) {
        this.type = type;
    }

    /**
     * Возвращает тип данных.
     * @return тип данных
     */
    String getType() {
        return this.type;
    }
}
