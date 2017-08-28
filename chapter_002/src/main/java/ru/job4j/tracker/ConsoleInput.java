package ru.job4j.tracker;

import java.util.Scanner;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения<br>
 * Обеспечить бесперебойную работу приложения Tracker.[#20170]<br>
 * @author imoskovtsev
 */
class ConsoleInput implements Input {
    /**
     * Слушаем ввод с консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     * @param question вопрос пользователю
     * @return String ответ пользователя
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     * @param question вопрос пользователю
     * @param range диапазон ответов
     * @return int ответ пользователя
     */
    @Override
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean isInRange = false;
        for (int value : range) {
            if (value == key) {
                isInRange = true;
                break;
            }
        }
        if (isInRange) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range");
        }
    }
}
