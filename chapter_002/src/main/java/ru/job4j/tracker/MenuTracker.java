package ru.job4j.tracker;

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
    public void execute(Input input, Tracker tracker) {
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
    /**
     * Пункты меню.
     */
    private final UserAction[] actions = new UserAction[7];

    /**
     * Конструктор.
     *
     * @param input   система ввода
     * @param tracker трекер задач
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Заполняем пункты меню.
     */
    public void fillActions() {
        this.actions[0] = this.new AddItem(Menu.ADD, "Add new Item");
        this.actions[1] = new MenuTracker.ShowAll(Menu.SHOW_ALL, "Show all items");
        this.actions[2] = new EditItem(Menu.EDIT, "Edit item");
        this.actions[3] = new MenuTracker.DeleteItem(Menu.DELETE, "Delete item");
        this.actions[4] = new MenuTracker.FindById(Menu.FIND_BY_ID, "Find item by Id");
        this.actions[5] = new MenuTracker.FindByName(Menu.FIND_BY_NAME, "Find item by name");
        this.actions[6] = new MenuTracker.ExitProgram(Menu.EXIT, "Exit Program");
    }

    /**
     * Выбор пункта меню.
     *
     * @param key пункт меню
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Отрисовка пунктов меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
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
        public void execute(Input input, Tracker tracker) {
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
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.printf("id заявки: %1$s, имя заявки: %2$s%3$s", item.getId(), item.getKey(), System.lineSeparator());
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
        public void execute(Input input, Tracker tracker) {
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
        public void execute(Input input, Tracker tracker) {
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
        public void execute(Input input, Tracker tracker) {
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
        public void execute(Input input, Tracker tracker) {
            StartUI.setIsDone(true);
        }
    }
}
