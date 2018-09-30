package ru.job4j.tracker;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * 3. Используя класс StubInput написать тесты проверяющие поведение пользователя [#14650]
 *
 * @author imoskovtsev
 */
public class StubInputTest {
    /**
     * Тест на пункт меню "0. Add new Item".
     */
    @Test
    public void whenUserAddsItemThenTrackerHasNewItem() {
        Tracker tracker = new Tracker(1);
        Input input = new StubInput(new String[]{
                String.valueOf(Menu.ADD),
                "test id",
                "test key",
                "test name",
                "test description",
                String.valueOf(Menu.EXIT)});
        new StartUI(input, tracker);
        StartUI.init();


        Item item = tracker.findAll()[0];
        assertThat(item.getId(), is("test id"));
        assertThat(item.getKey(), is("test key"));
        assertThat(item.getName(), is("test name"));
        assertThat(item.getDesc(), is("test description"));
    }

    /**
     * Тест на пункт меню "1. Show all items".
     */
    @Test
    public void whenUserWantsToSeeAllItemsThenTrackerShowsAllItems() {
        Tracker tracker = new Tracker(2);
        tracker.add(new Item("id1", "key1", "name1", "desc1", new Date().getTime(), new Date().getTime()));
        tracker.add(new Item("id2", "key2", "name2", "desc2", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{
                String.valueOf(Menu.SHOW_ALL),
                String.valueOf(Menu.EXIT)});
        new StartUI(input, tracker);
        StartUI.init();
        Item[] items = tracker.findAll();
        assertThat(items[0].getId(), is("id1"));
        assertThat(items[1].getId(), is("id2"));
    }

    /**
     * Тест на пункт меню "2. Edit item".
     */
    @Test
    public void whenUserEditsItemThenTrackerHasEditedItem() {
        Tracker tracker = new Tracker(1);
        tracker.add(new Item("id", "key", "name", "desc", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{
                String.valueOf(Menu.EDIT),
                "id",
                "updatedKey",
                "updatedName",
                "updatedDesc",
                String.valueOf(Menu.EXIT)});
        new StartUI(input, tracker);
        StartUI.init();
        Item item = tracker.findAll()[0];
        assertThat(item.getId(), is("id"));
        assertThat(item.getKey(), is("updatedKey"));
        assertThat(item.getName(), is("updatedName"));
        assertThat(item.getDesc(), is("updatedDesc"));
    }

    /**
     * Тест на пункт меню "3. Delete item".
     */
    @Test
    public void whenUserDeletesItemThenTrackerHasNoItem() {
        Tracker tracker = new Tracker(1);
        tracker.add(new Item("id", "key", "name", "desc", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{
                String.valueOf(Menu.DELETE),
                "id",
                String.valueOf(Menu.EXIT)});
        new StartUI(input, tracker);
        StartUI.init();
        Item[] allItems = tracker.findAll();
        Item[] expected = {};
        assertThat(allItems, is(expected));
    }

    /**
     * Тест на пункт меню "4. Find item by Id".
     */
    @Test
    public void whenUserSearchesByIdThenTrackerShowsItem() {
        Tracker tracker = new Tracker(2);
        tracker.add(new Item("id1", "key1", "name1", "desc1", new Date().getTime(), new Date().getTime()));
        tracker.add(new Item("id2", "key2", "name2", "desc2", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{
                String.valueOf(Menu.FIND_BY_ID),
                "id2",
                String.valueOf(Menu.EXIT)});
        new StartUI(input, tracker);
        StartUI.init();
        Item item = tracker.findById("id2");
        assertThat(item.getKey(), is("key2"));
    }

    /**
     * Тест на пункт меню "5. Find items by name".
     */
    @Test
    public void whenUserSearchesByNameThenTrackerShowsItem() {
        Tracker tracker = new Tracker(2);
        tracker.add(new Item("id1", "key1", "name1", "desc1", new Date().getTime(), new Date().getTime()));
        tracker.add(new Item("id2", "key2", "name2", "desc2", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{
                String.valueOf(Menu.FIND_BY_NAME),
                "name2",
                String.valueOf(Menu.EXIT)});
        new StartUI(input, tracker);
        StartUI.init();
        Item item = tracker.findByName("key2");
        assertThat(item.getId(), is("id2"));
    }

    /**
     * Тест на пункт меню "6. Exit Program".
     */
    @Test
    public void whenUserExitsThenTrackerProgramStops() {
        Tracker tracker = new Tracker(1);
        tracker.add(new Item("id1", "key1", "name1", "desc1", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{
                String.valueOf(Menu.EXIT)});
        new StartUI(input, tracker);
        StartUI.init();
        assertThat(StartUI.getIsDone(), is(true));
    }
}