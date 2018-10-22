package ru.job4j.tracker;

import java.util.List;

/**
 * 3. Используя класс StubInput написать тесты проверяющие поведение пользователя [#14650]<br>
 * Обеспечить бесперебойную работу приложения Tracker.[#20170]<br>
 * 1. Перегрузить метод ask ( int ask(String question, int[] range); ) в интерфейсе Input.<br>
 *
 * @author imoskovtsev
 */
public interface Input {
    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     *
     * @param question вопрос пользователю
     * @return String ответ пользователя
     */
    String ask(String question);

    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     *
     * @param question вопрос пользователю
     * @param range    диапазон ответов
     * @return int ответ пользователя
     */
    int ask(String question, List<Integer> range);
}
