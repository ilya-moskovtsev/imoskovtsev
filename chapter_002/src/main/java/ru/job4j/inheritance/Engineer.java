package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
class Engineer extends Profession {
    /**
     * Конструктор.
     * @param diploma диплом
     * @param name имя
     * @param address адрес
     * @param phoneNumber номер телефона
     */
    Engineer(String diploma, String name, String address, String phoneNumber) {
        super(diploma, name, address, phoneNumber);
    }

    /**
     * Исследовать данные.
     * @param data данные
     * @return String
     */
    String research(MyData data) {
        return String.format("Инженер %s исследует данные %s", this.getName(), data.getType());
    }

    /**
     * Проектировать данные.
     * @param data данные
     * @return String
     */
    String design(MyData data) {
        return String.format("Инженер %s проектирует данные %s", this.getName(), data.getType());
    }

    /**
     * Разрабатывать проект.
     * @param project проект
     * @return String
     */
    String develop(Project project) {
        return String.format("Инженер %s разрабатывает проект %s", this.getName(), project.getName());
    }

    /**
     * Тестировать проект.
     * @param project проект
     * @return String
     */
    String test(Project project) {
        return String.format("Инженер %s тестирует проект %s", this.getName(), project.getName());
    }
}
