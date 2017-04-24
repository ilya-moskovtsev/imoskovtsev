package ru.job4j.tracker;

import java.util.Scanner;

/**
 * 1. Используя класс ConsoleInput в классе StartUI обеспечить полноценную работу всего приложения
 * @author imoskovtsev
 */
public class StartUI {
    /**
     * точка входа.
     * @param args параметры запуска
     */
    public static void main(String[] args) {

        ConsoleInput input = new ConsoleInput();

        Scanner scanner = new Scanner(System.in);

        Tracker tracker = new Tracker(100);

        while (true) {
            System.out.println("0. Add new Item");
            System.out.println("1. Show all items");
            System.out.println("2. Edit item");
            System.out.println("3. Delete item");
            System.out.println("4. Find item by Id");
            System.out.println("5. Find items by name");
            System.out.println("6. Exit Program");
            System.out.print("Select: ");
            int select = scanner.nextInt();

            if (select == 0) {
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
            } else if (select == 1) {
                for (Item item : tracker.findAll()) {
                    System.out.printf("id заявки: %1$s, имя заявки: %2$s%3$s", item.getId(), item.getKey(), System.lineSeparator());
                }
            } else if (select == 2) {
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
            } else if (select == 3) {
                tracker.delete(
                        new Item(
                                input.ask("id заявки: "),
                                "", "", "", 0L, 0L
                        )
                );
            } else if (select == 4) {
                tracker.findById(input.ask("id заявки: "));
            } else if (select == 5) {
                tracker.findByName(input.ask("имя заявки: "));
            } else if (select == 6) {
                break;
            }
        }
    }
}
