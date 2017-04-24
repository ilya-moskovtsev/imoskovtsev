package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Проверить 2. Реализовать класс Tracker
 * @author imoskovtsev
 */
public class TrackerTest {
    /**
     * Класс трекер - это обертка над массивом заявок.
     */
    private Tracker tracker;
    /**
     * Заявка.
     */
    private Item item;
    /**
     * setUp.
     */
    @Before
    public void setUp() {
        tracker = new Tracker(10);
        item = new Item("id1", "key1", "name1", "desc1", new Date().getTime(), new Date().getTime());
    }

    /**
     * Проверить добавление заявок.
     */
    @Test
    public void add() {
        tracker.add(item);
        int resultItemCounter = tracker.getItemCounter();
        int expectedItemCounter = 1;
        assertThat(resultItemCounter, is(expectedItemCounter));

        String resultId = tracker.getItems()[expectedItemCounter - 1].getId();
        String expectedId = "id1";
        assertThat(resultId, is(expectedId));

        String resultKey = tracker.getItems()[expectedItemCounter - 1].getKey();
        String expectedKey = "key1";
        assertThat(resultKey, is(expectedKey));
    }

    /**
     * Проверить редактирование заявок.
     */
    @Test
    public void update() {
        tracker.add(item);
        long updatedCreateDate = new Date().getTime();
        long updatedUpdateDate = new Date().getTime();
        Item updateItem = new Item("id1", "updatedKey", "updatedName", "updatedDesc", updatedCreateDate, updatedUpdateDate);
        tracker.update(updateItem);

        String resultKey = tracker.getItems()[0].getKey();
        String expectedKey = "updatedKey";
        assertThat(resultKey, is(expectedKey));

        String resultName = tracker.getItems()[0].getName();
        String expectedName = "updatedName";
        assertThat(resultName, is(expectedName));

        String resultDesc = tracker.getItems()[0].getDesc();
        String expectedDesc = "updatedDesc";
        assertThat(resultDesc, is(expectedDesc));

        long resultCreateDate = tracker.getItems()[0].getCreateDate();
        assertThat(resultCreateDate, is(updatedCreateDate));

        long resultUpdateDate = tracker.getItems()[0].getUpdateDate();
        assertThat(resultUpdateDate, is(updatedUpdateDate));
    }

    /**
     * Проверить удаление заявок.
     */
    @Test(expected = NullPointerException.class)
    public void delete() {
        tracker.add(item);
        tracker.delete(item);
        Item resultItem = tracker.getItems()[0];
    }

    /**
     * Проверить получение списка всех заявок.
     */
    @Test
    public void findAll() {
        tracker.add(item);

        int resultLength = tracker.findAll().length;
        int expectedLength = 1;
        assertThat(resultLength, is(expectedLength));

        String resultId = tracker.findAll()[expectedLength - 1].getId();
        String expectedId = "id1";
        assertThat(resultId, is(expectedId));

        String resultKey = tracker.findAll()[expectedLength - 1].getKey();
        String expectedKey = "key1";
        assertThat(resultKey, is(expectedKey));
    }

    /**
     * Проверить получение списка по имени.
     */
    @Test
    public void findByName() {
        tracker.add(item);

        String resultId = tracker.findByName("key1").getId();
        String expectedId = "id1";
        assertThat(resultId, is(expectedId));

        String resultKey = tracker.findByName("key1").getKey();
        String expectedKey = "key1";
        assertThat(resultKey, is(expectedKey));
    }

    /**
     * Проверить получение заявки по id.
     */
    @Test
    public void findById() {
        tracker.add(item);

        String resultId = tracker.findById("id1").getId();
        String expectedId = "id1";
        assertThat(resultId, is(expectedId));

        String resultKey = tracker.findById("id1").getKey();
        String expectedKey = "key1";
        assertThat(resultKey, is(expectedKey));
    }

}