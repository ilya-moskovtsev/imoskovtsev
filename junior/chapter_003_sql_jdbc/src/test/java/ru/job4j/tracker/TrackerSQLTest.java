package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

class TrackerSQLTest {
    private static final Logger LOG = LogManager.getLogger(TrackerSQLTest.class.getName());
    public static final String ID_1 = "1";
    public static final String KEY_1 = "key1";
    public static final String NAME_1 = "name1";
    public static final String DESC_1 = "desc1";
    public static final String UPDATED_KEY = "updatedKey";
    public static final String UPDATED_NAME = "updatedName";
    public static final String UPDATED_DESC = "updatedDesc";
    public static final String ID_2 = "2";
    public static final String KEY_2 = "key2";
    public static final String NAME_2 = "name2";
    public static final String DESC_2 = "desc2";
    public static final String ID_3 = "3";
    public static final String NAME_3 = "name3";
    public static final String DESC_3 = "desc3";
    public static final int INT_0 = 0;
    public static final int INT_1 = 1;
    public static final int INT_2 = 2;

    public Connection init() {
        Connection conn = null;
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream(TrackerSQL.APP_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty(TrackerSQL.DRIVER_CLASS_NAME));
            conn = DriverManager.getConnection(
                    properties.getProperty(TrackerSQL.URL),
                    properties.getProperty(TrackerSQL.USER),
                    properties.getProperty(TrackerSQL.PASSWORD)
            );
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return conn;
    }

    @Test
    void checkConnection() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        assertThat(tracker.init(), is(true));
    }

    @Test
    public void add() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        tracker.init();
        final long TIME = new Date().getTime();
        Item item = new Item(ID_1, KEY_1, NAME_1, DESC_1, TIME, TIME);
        tracker.add(item);
        assertThat(tracker.getItemCounter(), is(INT_1));

        Item resultItem = tracker.getItems().get(INT_0);
        assertThat(resultItem.getKey(), is(KEY_1));
        assertThat(resultItem.getName(), is(NAME_1));
        assertThat(resultItem.getDesc(), is(DESC_1));
    }

    @Test
    public void update() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        tracker.init();
        final long TIME = new Date().getTime();
        Item item = new Item(ID_1, KEY_1, NAME_1, DESC_1, TIME, TIME);
        tracker.add(item);

        final long UPDATE_TIME = new Date().getTime();
        tracker.update(new Item(ID_1, UPDATED_KEY, UPDATED_NAME, UPDATED_DESC, UPDATE_TIME, UPDATE_TIME));

        Item resultItem = tracker.getItems().get(INT_0);
        assertThat(resultItem.getKey(), is(UPDATED_KEY));
        assertThat(resultItem.getName(), is(UPDATED_NAME));
        assertThat(resultItem.getDesc(), is(UPDATED_DESC));
    }

    @Test
    public void delete() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        tracker.init();
        final long TIME = new Date().getTime();
        Item item = new Item(ID_1, KEY_1, NAME_1, DESC_1, TIME, TIME);
        tracker.add(item);

        assertThat(tracker.getItems().size(), is(INT_1));
        tracker.delete(item);
        assertThat(tracker.getItems().size(), is(INT_0));
    }

    @Test
    public void findAll() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        tracker.init();
        final long TIME = new Date().getTime();
        tracker.add(new Item(ID_1, KEY_1, NAME_1, DESC_1, TIME, TIME));
        tracker.add(new Item(ID_2, KEY_2, NAME_2, DESC_2, TIME, TIME));

        List<Item> resultList = tracker.findAll();
        assertThat(resultList.size(), is(INT_2));
        assertThat(resultList.get(INT_0).getKey(), is(KEY_1));
        assertThat(resultList.get(INT_1).getKey(), is(KEY_2));
    }

    @Test
    public void findByName() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        tracker.init();
        final long TIME = new Date().getTime();
        tracker.add(new Item(ID_1, KEY_1, NAME_1, DESC_1, TIME, TIME));
        tracker.add(new Item(ID_2, KEY_2, NAME_2, DESC_2, TIME, TIME));
        tracker.add(new Item(ID_3, KEY_1, NAME_3, DESC_3, TIME, TIME));

        List<Item> resultList = tracker.findByName(KEY_1);
        assertThat(resultList.size(), is(INT_2));
        assertThat(resultList.get(INT_0).getName(), is(NAME_1));
        assertThat(resultList.get(INT_1).getDesc(), is(DESC_3));
    }

    @Test
    public void findById() throws SQLException {
        TrackerSQL tracker = new TrackerSQL(ConnectionRollback.create(this.init()));
        tracker.init();
        final long TIME = new Date().getTime();
        tracker.add(new Item(ID_1, KEY_1, NAME_1, DESC_1, TIME, TIME));
        tracker.add(new Item(ID_2, KEY_2, NAME_2, DESC_2, TIME, TIME));

        assertThat(tracker.findById(ID_1).getKey(), is(KEY_1));
        assertThat(tracker.findById(ID_2).getName(), is(NAME_2));
    }
}