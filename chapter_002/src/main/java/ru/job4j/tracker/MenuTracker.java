package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Edit item.
 */
class EditItem extends BaseAction {

    /**
     * Конструктор.
     *
     * @param key  Номер пункта меню.
     * @param name Название пунка меню.
     */
    public EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public void execute(Input input, Tracker tracker, Consumer<String> output) {
        tracker.update(
                new Item(
                        input.ask("Please, enter the task's id: "),
                        input.ask("Please, enter the task's key: "),
                        input.ask("Please, enter the task's name: "),
                        input.ask("Please, enter the tasks's desc: "),
                        System.currentTimeMillis(),
                        System.currentTimeMillis()
                )
        );
    }
}

/**
 * Реализовать события на внутренних классах. [#15201]<br>
 * Класс MenuTracker, который реализует меню.<br>
 * В классе MenuTracker реализованы внутренние классы событий: добавление, редактирование, удаление, поиск по имени, поиск по id и вывод на экран всех заявок.<br>
 * При реализации событий использованы статический внутренний класс, не статический внутренний класс и внешний класс, расположенный в одном файле с MenuTracker.<br>
 * <br>
 * Нужно сделать абстрактный класс BaseAction с конструктором [#23232]<br>
 * 4. Все события наследовать от BaseAction.<br>
 *
 * @author imoskovtsev
 */
public class MenuTracker {

    /**
     * Система ввода.
     */
    private final Input input;
    /**
     * Трекер задач.
     */
    private final Tracker tracker;
    private final Consumer<String> output;
    /**
     * Пункты меню.
     */
    private final List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   система ввода
     * @param tracker трекер задач
     * @param output
     */
    public MenuTracker(Input input, Tracker tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }

    /**
     * Заполняем пункты меню.
     */
    public void fillActions() {
        actions.add(new AddItem(Menu.ADD, "Add new Item"));
        actions.add(new MenuTracker.ShowAll(Menu.SHOW_ALL, "Show all items"));
        actions.add(new EditItem(Menu.EDIT, "Edit item"));
        actions.add(new MenuTracker.DeleteItem(Menu.DELETE, "Delete item"));
        actions.add(new MenuTracker.FindById(Menu.FIND_BY_ID, "Find item by Id"));
        actions.add(new MenuTracker.FindByName(Menu.FIND_BY_NAME, "Find item by name"));
        actions.add(new MenuTracker.ExitProgram(Menu.EXIT, "Exit Program"));
    }

    /**
     * Выбор пункта меню.
     *
     * @param key пункт меню
     */
    public void select(int key) {
        actions.get(key).execute(input, tracker, output);
    }

    /**
     * Отрисовка пунктов меню.
     */
    public void show() {
        for (UserAction action : actions) {
            if (action != null) {
                output.accept(action.info());
            }
        }
    }

    /**
     * Add new Item.
     */
    private class AddItem extends BaseAction {

        /**
         * Конструктор.
         *
         * @param key  Номер пункта меню.
         * @param name Название пунка меню.
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            tracker.add(
                    new Item(
                            input.ask("Please, enter the task's id: "),
                            input.ask("Please, enter the task's key: "),
                            input.ask("Please, enter the task's name: "),
                            input.ask("Please, enter the tasks's desc: "),
                            System.currentTimeMillis(),
                            System.currentTimeMillis()
                    )
            );
        }
    }

    /**
     * Show all items.
     */
    private static class ShowAll extends BaseAction {

        /**
         * Конструктор.
         *
         * @param key  Номер пункта меню.
         * @param name Название пунка меню.
         */
        public ShowAll(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            for (Item item : tracker.findAll()) {
                output.accept(String.format("id заявки: %1$s, имя заявки: %2$s%3$s", item.getId(), item.getKey(), System.lineSeparator()));
            }
        }
    }

    /**
     * Delete item.
     */
    private static class DeleteItem extends BaseAction {

        /**
         * Конструктор.
         *
         * @param key  Номер пункта меню.
         * @param name Название пунка меню.
         */
        public DeleteItem(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            tracker.delete(
                    new Item(
                            input.ask("id заявки: "),
                            "", "", "", 0L, 0L
                    )
            );
        }
    }

    /**
     * Find item by Id.
     */
    private static class FindById extends BaseAction {

        /**
         * Конструктор.
         *
         * @param key  Номер пункта меню.
         * @param name Название пунка меню.
         */
        public FindById(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            tracker.findById(input.ask("id заявки: "));
        }
    }

    /**
     * Find item by name.
     */
    private static class FindByName extends BaseAction {

        /**
         * Конструктор.
         *
         * @param key  Номер пункта меню.
         * @param name Название пунка меню.
         */
        public FindByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            tracker.findByName(input.ask("имя заявки: "));
        }
    }

    /**
     * Exit Program.
     */
    private static class ExitProgram extends BaseAction {

        /**
         * Конструктор.
         *
         * @param key  Номер пункта меню.
         * @param name Название пунка меню.
         */
        public ExitProgram(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker, Consumer<String> output) {
            StartUI.setIsDone(true);
        }
    }
}
