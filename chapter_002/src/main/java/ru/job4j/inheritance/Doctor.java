package ru.job4j.inheritance;

/**
 * 1. Реализация профессий в коде
 *
 * @author imoskovtsev
 */
public class Doctor extends Profession {
    /**
     * Конструктор.
     *
     * @param diploma     диплом
     * @param name        имя
     * @param address     адрес
     * @param phoneNumber номер телефона
     */
    public Doctor(String diploma, String name, String address, String phoneNumber) {
        super(diploma, name, address, phoneNumber);
    }

    /**
     * Обследовать пациента.
     *
     * @param patient пациент
     * @return String
     */
    public String checkPatient(Patient patient) {
        return String.format("Доктор %s обследует пациента %s", this.getName(), patient.getName());
    }

    /**
     * Выписать лекарства.
     *
     * @param patient пациент
     * @return String
     */
    public String prescribeMedicines(Patient patient) {
        return String.format("Доктор %s выписывает лекарства пациенту %s", this.getName(), patient.getName());
    }

    /**
     * Назначить анализы.
     *
     * @param patient пациент
     * @return String
     */
    public String prescribeTests(Patient patient) {
        return String.format("Доктор %s назначает анализы пациенту %s", this.getName(), patient.getName());
    }

    /**
     * Confirmation of doctor's qualification.
     */
    public String confirmQualification() {
        return String.format("Доктор %s подтверждает диплом %s", this.getName(), this.getDiploma());
    }

    /**
     * Give contact information to a patient.
     */
    public String provideContactInformation(Patient patient) {
        return String.format("Доктор %s передоставляет контактную информацию (адрес: %s, телефон: %s) пациенту %s",
                this.getName(),
                this.getAddress(),
                this.getPhoneNumber(),
                patient.getName());
    }
}
