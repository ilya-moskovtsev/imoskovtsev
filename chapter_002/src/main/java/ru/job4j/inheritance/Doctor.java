package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
class Doctor extends Profession {
    /**
     * Конструктор.
     * @param diploma диплом
     * @param name имя
     * @param address адрес
     * @param phoneNumber номер телефона
     */
    Doctor(String diploma, String name, String address, String phoneNumber) {
        super(diploma, name, address, phoneNumber);
    }

    /**
     * Обследовать пациента.
     * @param patient пациент
     * @return String
     */
    String checkPatient(Patient patient) {
        return String.format("Доктор %s обследует пациента %s", this.getName(), patient.getName());
    }

    /**
     * Выписать лекарства.
     * @param patient пациент
     * @return String
     */
    String prescribeMedicines(Patient patient) {
        return String.format("Доктор %s выписывает лекарства пациенту %s", this.getName(), patient.getName());
    }

    /**
     * Назначить анализы.
     * @param patient пациент
     * @return String
     */
    String prescribeTests(Patient patient) {
        return String.format("Доктор %s назначает анализы пациенту %s", this.getName(), patient.getName());
    }
}
