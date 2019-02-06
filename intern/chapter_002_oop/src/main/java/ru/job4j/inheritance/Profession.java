package ru.job4j.inheritance;

/**
 * 1. Реализация профессий в коде
 *
 * @author imoskovtsev
 */
public abstract class Profession {
    /**
     * Диплом.
     */
    private final String diploma;
    /**
     * Имя.
     */
    private final String name;
    /**
     * Адрес.
     */
    private final String address;
    /**
     * Номер телефона.
     */
    private final String phoneNumber;

    /**
     * Конструктор.
     *
     * @param diploma     диплом
     * @param name        имя
     * @param address     адрес
     * @param phoneNumber номер телефона
     */
    public Profession(String diploma, String name, String address, String phoneNumber) {
        this.diploma = diploma;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Возвращает diploma.
     *
     * @return diploma
     */
    public String getDiploma() {
        return diploma;
    }

    /**
     * Возвращает name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает address.
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Возвращает phoneNumber.
     *
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}
