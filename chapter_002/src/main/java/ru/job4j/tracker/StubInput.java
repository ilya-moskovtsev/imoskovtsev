package ru.job4j.tracker;

import java.util.List;

/**
 * 3. Используя класс StubInput написать тесты проверяющие поведение пользователя [#14650]<br>
 * Обеспечить бесперебойную работу приложения Tracker.[#20170]<br>
 *
 * @author imoskovtsev
 */
public class StubInput implements Input {
    /**
     * Ответы пользователя.
     */
    private final List<String> answers;
    /**
     * Answers counter.
     */
    private int position;

    /**
     * Конструктор.
     *
     * @param answers задаем ответы пользователя
     */
    public StubInput(List<String> answers) {
        this.answers = answers;
    }

    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     *
     * @param question вопрос пользователю
     * @return String ответ пользователя
     */
    @Override
    public String ask(String question) {
        return answers.get(position++);
    }

    @Override
    public int ask(String question, List<Integer> range) {
        return Integer.parseInt(answers.get(position++));
    }
}
