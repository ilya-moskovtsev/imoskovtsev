package ru.job4j.inheritance;
/**
 * 1. Реализация профессий в коде
 * @author imoskovtsev
 */
class Teacher extends Profession {
    /**
     * Конструктор.
     * @param diploma диплом
     * @param name имя
     * @param address адрес
     * @param phoneNumber номер телефона
     */
    Teacher(String diploma, String name, String address, String phoneNumber) {
        super(diploma, name, address, phoneNumber);
    }

    /**
     * Подготовить урок.
     * @param lesson урок
     * @return String
     */
    String prepareLesson(Lesson lesson) {
        return String.format("Учитель %s подготавливает урок %s", this.getName(), lesson.getName());
    }

    /**
     * Провести урок.
     * @param lesson урок
     * @return String
     */
    String giveLesson(Lesson lesson) {
        return String.format("Учитель %s проводит урок %s", this.getName(), lesson.getName());
    }

    /**
     * Оценить прогресс ученика.
     * @param student ученик
     * @return String
     */
    String assesStudentProgress(Student student) {
        return String.format("Учитель %s оценивает прогресс ученика %s", this.getName(), student.getName());
    }
}
