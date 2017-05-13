package ru.job4j.tracker;

/**
 *  3. Используя класс StubInput написать тесты проверяющие поведение пользователя [#14650]
 * @author imoskovtsev
 */
public class StubInput implements Input {
    /**
     * Ответы пользователя.
     */
    private String[] answers;
    /**
     * Cчётчик ответов.
     */
    private int position;

    /**
     * Конструктор.
     * @param answers задаем ответы пользователя
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     * @param question вопрос пользователю
     * @return String ответ пользователя
     */
    @Override
    public String ask(String question) {
        return answers[position++];
    }
}