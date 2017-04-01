package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
class Patient extends Profession {
    /**
     * Конструктор.
     *
     * @param diploma     диплом
     * @param name        имя
     * @param address     адрес
     * @param phoneNumber номер телефона
     */
    Patient(String diploma, String name, String address, String phoneNumber) {
        super(diploma, name, address, phoneNumber);
    }
}
