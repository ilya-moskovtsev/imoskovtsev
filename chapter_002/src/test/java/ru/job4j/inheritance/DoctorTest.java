package ru.job4j.inheritance;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Проверить 1. Реализация профессий в коде
 * @author imoskovtsev
 */
public class DoctorTest {
    /**
     * Доктор.
     */
    private static Doctor doctor;
    /**
     * Пациент.
     */
    private static Patient patient;

    /**
     * Подготовка к тестам.
     */
    @BeforeClass
    public static void beforeClass() {
        doctor = new Doctor("Медицинский", "Иван", "Москва", "495");
        patient = new Patient("Инженерный", "Сергей", "Москва", "495");
    }

    /**
     * Проверить Назначить анализы.
     */
    @Test
    public void prescribeTests() {
        String result = doctor.prescribeTests(patient);
        String expected = "Доктор Иван назначает анализы пациенту Сергей";
        assertThat(result, is(expected));
    }

    /**
     * Проверить Выписать лекарства.
     */
    @Test
    public void prescribeMedicines() {
        String result = doctor.prescribeMedicines(patient);
        String expected = "Доктор Иван выписывает лекарства пациенту Сергей";
        assertThat(result, is(expected));
    }

    /**
     * Проверить Обследовать пациента.
     */
    @Test
    public void checkPatient() {
        String result = doctor.checkPatient(patient);
        String expected = "Доктор Иван обследует пациента Сергей";
        assertThat(result, is(expected));
    }

}