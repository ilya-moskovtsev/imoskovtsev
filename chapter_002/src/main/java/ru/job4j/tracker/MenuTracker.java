package ru.job4j.tracker;

/**
 * Edit item.
 */
class EditItem implements UserAction {

    @Override
    public int key() {
        return 2;
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

    @Override
    public String info() {
        return String.format("%s. %s", this.key(), "Edit item");
    }
}

/**
 * Реализовать события на внутренних классах. [#15201]
 * Класс MenuTracker, который реализует меню.
 * В классе MenuTracker реализованы внутренние классы событий: добавление, редактирование, удаление, поиск по имени, поиск по id и вывод на экран всех заявок.
 * При реализации событий использованы статический внутренний класс, не статический внутренний класс и внешний класс, расположенный в одном файле с MenuTracker.
 *
 * @author imoskovtsev
 */
public class MenuTracker {

    /**
     * Система ввода.
     */
    private Input input;
    /**
     * Трекер задач.
     */
    private Tracker tracker;
    /**
     * Пункты меню.
     */
    private UserAction[] actions = new UserAction[7];

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
        this.actions[0] = this.new AddItem();
        this.actions[1] = new MenuTracker.ShowAll();
        this.actions[2] = new EditItem();
        this.actions[3] = new MenuTracker.DeleteItem();
        this.actions[4] = new MenuTracker.FindById();
        this.actions[5] = new MenuTracker.FindByName();
        this.actions[6] = new MenuTracker.ExitProgram();
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
    private class AddItem implements UserAction {

        @Override
        public int key() {
            return 0;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Add new Item");
        }
    }

    /**
     * Show all items.
     */
    private static class ShowAll implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            for (Item item : tracker.findAll()) {
                System.out.printf("id заявки: %1$s, имя заявки: %2$s%3$s", item.getId(), item.getKey(), System.lineSeparator());
            }
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    /**
     * Delete item.
     */
    private static class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
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

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * Find item by Id.
     */
    private static class FindById implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.findById(input.ask("id заявки: "));
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by Id");
        }
    }

    /**
     * Find item by name.
     */
    private static class FindByName implements UserAction {

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            tracker.findByName(input.ask("имя заявки: "));
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by name");
        }
    }

    /**
     * Exit Program.
     */
    private static class ExitProgram implements UserAction {

        @Override
        public int key() {
            return 6;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            StartUI.setIsDone(true);
        }

        @Override
        public String info() {
            return String.format("%s. %s", this.key(), "Exit Program");
        }
    }
}
