package ru.job4j.tracker;

import java.util.Scanner;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения
 * @author imoskovtsev
 */
class ConsoleInput {
    /**
     * Слушаем ввод с консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Задаем пользователю вопрос. Возвращаем ответ пользователя.
     * @param question вопрос пользователю
     * @return String ответ пользователя
     */
    String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }
}
