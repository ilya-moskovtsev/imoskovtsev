package ru.job4j.inheritance;

/**
 * 1. Реализация профессий в коде
 *
 * @author imoskovtsev
 */
public class Patient extends Profession {
    /**
     * Конструктор.
     *
     * @param diploma     диплом
     * @param name        имя
     * @param address     адрес
     * @param phoneNumber номер телефона
     */
    public Patient(String diploma, String name, String address, String phoneNumber) {
        super(diploma, name, address, phoneNumber);
    }
}
