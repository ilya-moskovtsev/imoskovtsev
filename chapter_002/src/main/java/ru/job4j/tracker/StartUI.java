package ru.job4j.tracker;

import static ru.job4j.tracker.Menu.ADD;
import static ru.job4j.tracker.Menu.SHOW_ALL;
import static ru.job4j.tracker.Menu.EDIT;
import static ru.job4j.tracker.Menu.DELETE;
import static ru.job4j.tracker.Menu.FIND_BY_ID;
import static ru.job4j.tracker.Menu.FIND_BY_NAME;
import static ru.job4j.tracker.Menu.EXIT;


/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения
 * @author imoskovtsev
 */
public class StartUI {
    /**
     * выбранный ввод.
     */
    private static Input input;
    /**
     * выбранный трекер.
     */
    private static Tracker tracker;

    /**
     * Конструктор.
     * @param input задаем ввод
     * @param tracker задаем трекер
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * точка входа.
     * @param args параметры запуска
     */
    public static void main(String[] args) {
        init();
    }

    /**
     * Инициализация.
     */
    static void init() {
        boolean isDone = false;
        while (!isDone) {
            System.out.println("0. Add new Item");
            System.out.println("1. Show all items");
            System.out.println("2. Edit item");
            System.out.println("3. Delete item");
            System.out.println("4. Find item by Id");
            System.out.println("5. Find items by name");
            System.out.println("6. Exit Program");
            int select = Integer.valueOf(input.ask("Select: "));

            if (select == ADD) {
                tracker.add(
                        new Item(
                                input.ask("id заявки: "),
                                input.ask("имя заявки: "),
                                input.ask("имя ответственного: "),
                                input.ask("описание заявки: "),
                                System.currentTimeMillis(),
                                System.currentTimeMillis()
                        )
                );
            } else if (select == SHOW_ALL) {
                for (Item item : tracker.findAll()) {
                    System.out.printf("id заявки: %1$s, имя заявки: %2$s%3$s", item.getId(), item.getKey(), System.lineSeparator());
                }
            } else if (select == EDIT) {
                tracker.update(
                        new Item(
                                input.ask("id заявки: "),
                                input.ask("имя заявки: "),
                                input.ask("имя ответственного: "),
                                input.ask("описание заявки: "),
                                System.currentTimeMillis(),
                                System.currentTimeMillis()
                        )
                );
            } else if (select == DELETE) {
                tracker.delete(
                        new Item(
                                input.ask("id заявки: "),
                                "", "", "", 0L, 0L
                        )
                );
            } else if (select == FIND_BY_ID) {
                tracker.findById(input.ask("id заявки: "));
            } else if (select == FIND_BY_NAME) {
                tracker.findByName(input.ask("имя заявки: "));
            } else if (select == EXIT) {
                isDone = true;
            }
        }
    }
}
