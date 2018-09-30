package ru.job4j.tracker;

/**
 * Обеспечить бесперебойную работу приложения Tracker.[#20170]<br>
 * 3. Создать класс ValidateInput, наследующий ConsoleInput.<br>
 * В нем переопределите метод ask таким образом,<br>
 * что бы обрабатывались исключительные ситуации<br>
 * (при помощи блоков try { … } catch( … ) { … }).<br>
 * Пример:<br>
 * При выборе пункта меню -1, программа должна вывести на экран сообщение о том,<br>
 * что необходимо выбрать значение из диапазона меню и запросить повторно ввод.<br>
 * При выборе пункта меню ‘a’, программа должна вывести на экран сообщение о том,<br>
 * что необходимо ввести корректное значение и запросить повторно ввод.<br>
 *
 * @author imoskovtsev
 */
public class ValidateInput extends ConsoleInput {
    @Override
    public int ask(String question, int[] range) {
        boolean isValid = false;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                isValid = true;
            } catch (MenuOutException moe) {
                System.out.println("Please select a key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            }
        } while (!isValid);
        return value;
    }
}
