package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
public abstract class Profession {
    /**
     * Диплом.
     */
    private String diploma;
    /**
     * Имя.
     */
    private String name;
    /**
     * Адрес.
     */
    private String address;
    /**
     * Номер телефона.
     */
    private String phoneNumber;

    /**
     * Конструктор.
     * @param diploma диплом
     * @param name имя
     * @param address адрес
     * @param phoneNumber номер телефона
     */
    Profession(String diploma, String name, String address, String phoneNumber) {
        this.diploma = diploma;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Возвращает diploma.
     * @return diploma
     */
    public String getDiploma() {
        return diploma;
    }

    /**
     * Возвращает name.
     * @return name
     */
    String getName() {
        return name;
    }

    /**
     * Возвращает address.
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Возвращает phoneNumber.
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
