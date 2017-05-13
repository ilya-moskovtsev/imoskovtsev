package ru.job4j.tracker;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * 3. Используя класс StubInput написать тесты проверяющие поведение пользователя [#14650]
 * @author imoskovtsev
 */
public class StubInputTest {
    /**
     * Тест на пункт меню "0. Add new Item".
     */
    @Test
    public void whenUserAddsItemThenTrackerHasNewItem() {
        Tracker tracker = new Tracker(1);
        Input input = new StubInput(new String[]{"0", "test id", "test key", "test name", "test description", "6"});
        new StartUI(input, tracker).init();
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
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
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
        Input input = new StubInput(new String[]{"2", "id", "updatedKey", "updatedName", "updatedDesc", "6"});
        new StartUI(input, tracker).init();
        Item item = tracker.findAll()[0];
        assertThat(item.getId(), is("id"));
        assertThat(item.getKey(), is("updatedKey"));
        assertThat(item.getName(), is("updatedName"));
        assertThat(item.getDesc(), is("updatedDesc"));
    }

    /**
     * Тест на пункт меню "3. Delete item".
     */
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void whenUserDeletesItemThenTrackerHasNoItem() {
        Tracker tracker = new Tracker(1);
        tracker.add(new Item("id", "key", "name", "desc", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{"3", "id", "6"});
        new StartUI(input, tracker).init();
        Item item = tracker.findAll()[0];
    }

    /**
     * Тест на пункт меню "4. Find item by Id".
     */
    @Test
    public void whenUserSearchesByIdThenTrackerShowsItem() {
        Tracker tracker = new Tracker(2);
        tracker.add(new Item("id1", "key1", "name1", "desc1", new Date().getTime(), new Date().getTime()));
        tracker.add(new Item("id2", "key2", "name2", "desc2", new Date().getTime(), new Date().getTime()));
        Input input = new StubInput(new String[]{"4", "id2", "6"});
        new StartUI(input, tracker).init();
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
        Input input = new StubInput(new String[]{"4", "id2", "6"});
        new StartUI(input, tracker).init();
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
        Input input = new StubInput(new String[]{"6"});
        new StartUI(input, tracker).init();
    }
}