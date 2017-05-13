package ru.job4j.tracker;

/**
 * 3. Используя класс StubInput написать тесты проверяющие поведение пользователя [#14650]
 * @author imoskovtsev
 */
interface Input {
    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     * @param question вопрос пользователю
     * @return String ответ пользователя
     */
    String ask(String question);
}
