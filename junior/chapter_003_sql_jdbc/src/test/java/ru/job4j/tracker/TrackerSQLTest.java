package ru.job4j.tracker;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@Disabled
class TrackerSQLTest {
    @Test
    void checkConnection() {
        TrackerSQL tracker = new TrackerSQL();
        assertThat(tracker.init(), is(true));
    }

    @Test
    public void add() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        final long TIME = new Date().getTime();
        Item item = new Item("id1", "key1", "name1", "desc1", TIME, TIME);
        tracker.add(item);
        assertThat(tracker.getItemCounter(), is(1));

        Item resultItem = tracker.getItems().get(0);
        assertThat(resultItem.getKey(), is("key1"));
        assertThat(resultItem.getName(), is("name1"));
        assertThat(resultItem.getDesc(), is("desc1"));
    }

    @Test
    public void update() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        final long TIME = new Date().getTime();
        Item item = new Item("1", "key1", "name1", "desc1", TIME, TIME);
        tracker.add(item);

        final long UPDATE_TIME = new Date().getTime();
        tracker.update(new Item("1", "updatedKey", "updatedName", "updatedDesc", UPDATE_TIME, UPDATE_TIME));

        Item resultItem = tracker.getItems().get(0);
        assertThat(resultItem.getKey(), is("updatedKey"));
        assertThat(resultItem.getName(), is("updatedName"));
        assertThat(resultItem.getDesc(), is("updatedDesc"));
    }

    @Test
    public void delete() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        final long TIME = new Date().getTime();
        Item item = new Item("1", "key1", "name1", "desc1", TIME, TIME);
        tracker.add(item);

        assertThat(tracker.getItems().size(), is(1));
        tracker.delete(item);
        assertThat(tracker.getItems().size(), is(0));
    }

    @Test
    public void findAll() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        final long TIME = new Date().getTime();
        tracker.add(new Item("1", "key1", "name1", "desc1", TIME, TIME));
        tracker.add(new Item("2", "key2", "name2", "desc2", TIME, TIME));

        List<Item> resultList = tracker.findAll();
        assertThat(resultList.size(), is(2));
        assertThat(resultList.get(0).getKey(), is("key1"));
        assertThat(resultList.get(1).getKey(), is("key2"));
    }

    @Test
    public void findByName() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        final long TIME = new Date().getTime();
        tracker.add(new Item("1", "key1", "name1", "desc1", TIME, TIME));
        tracker.add(new Item("2", "key2", "name2", "desc2", TIME, TIME));
        tracker.add(new Item("3", "key1", "name3", "desc3", TIME, TIME));

        List<Item> resultList = tracker.findByName("key1");
        assertThat(resultList.size(), is(2));
        assertThat(resultList.get(0).getName(), is("name1"));
        assertThat(resultList.get(1).getDesc(), is("desc3"));
    }

    @Test
    public void findById() {
        TrackerSQL tracker = new TrackerSQL();
        tracker.init();
        final long TIME = new Date().getTime();
        tracker.add(new Item("1", "key1", "name1", "desc1", TIME, TIME));
        tracker.add(new Item("2", "key2", "name2", "desc2", TIME, TIME));

        assertThat(tracker.findById("1").getKey(), is("key1"));
        assertThat(tracker.findById("2").getName(), is("name2"));
    }
}