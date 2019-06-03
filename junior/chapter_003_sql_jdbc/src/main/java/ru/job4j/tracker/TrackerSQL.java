package ru.job4j.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TrackerSQL implements ITracker, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());
    // properties
    public static final String APP_PROPERTIES = "app.properties";
    public static final String URL = "url";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String DRIVER_CLASS_NAME = "driver-class-name";
    // queries
    public static final String CREATE_ITEMS = "create table items(id serial primary key,name varchar(200),assignee varchar(200),description text,create_date timestamp,update_date timestamp)";
    public static final String IDS = "select id from items";
    public static final String GET_ITEMS = "select * from items";
    public static final String GET_ITEM_COUNTER = "select count(id) from items";
    // prepared statements
    public static final String ADD_ITEM = "insert into items (name, assignee, description, create_date, update_date) values (? ,? ,? ,? ,?)";
    public static final String UPDATE = "update items set name = ?, assignee = ?, description = ?, create_date = ?, update_date = ? where id = ?";
    public static final String DELETE = "delete from items where id = ?";
    // column label
    public static final String COUNT = "count";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String ASSIGNEE = "assignee";
    public static final String DESCRIPTION = "description";
    public static final String CREATE_DATE = "create_date";
    public static final String UPDATE_DATE = "update_date";

    private Connection conn;

    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream(APP_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(in);
            Class.forName(properties.getProperty(DRIVER_CLASS_NAME));
            this.conn = DriverManager.getConnection(
                    properties.getProperty(URL),
                    properties.getProperty(USER),
                    properties.getProperty(PASSWORD));
            createItemsIfNotExists();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return this.conn != null;
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new LinkedList<>();
        try (final Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(GET_ITEMS)) {
            while (resultSet.next()) {
                items.add(new Item(
                        resultSet.getString(ID),
                        resultSet.getString(NAME),
                        resultSet.getString(ASSIGNEE),
                        resultSet.getString(DESCRIPTION),
                        resultSet.getTimestamp(CREATE_DATE).getTime(),
                        resultSet.getTimestamp(UPDATE_DATE).getTime()
                ));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public int getItemCounter() {
        int itemCounter = 0;
        try (final Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(GET_ITEM_COUNTER)) {
            if (resultSet.next()) {
                itemCounter = resultSet.getInt(COUNT);
            }

        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return itemCounter;
    }

    @Override
    public void add(Item item) {
        try (final PreparedStatement st = conn.prepareStatement(ADD_ITEM)) {
            st.setString(1, item.getKey());
            st.setString(2, item.getName());
            st.setString(3, item.getDesc());
            st.setTimestamp(4, new Timestamp(item.getCreateDate()));
            st.setTimestamp(5, new Timestamp(item.getUpdateDate()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Item item) {
        try (final PreparedStatement st = conn.prepareStatement(UPDATE)) {
            st.setString(1, item.getKey());
            st.setString(2, item.getName());
            st.setString(3, item.getDesc());
            st.setTimestamp(4, new Timestamp(item.getCreateDate()));
            st.setTimestamp(5, new Timestamp(item.getUpdateDate()));
            st.setInt(6, Integer.parseInt(item.getId()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(Item item) {
        try (final PreparedStatement st = conn.prepareStatement(DELETE)) {
            st.setInt(1, Integer.parseInt(item.getId()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Item> findAll() {
        return getItems();
    }

    @Override
    public List<Item> findByName(String key) {
        return getItems().stream().filter(item -> item != null && item.getKey().equals(key)).collect(Collectors.toList());
    }

    @Override
    public Item findById(String id) {
        return getItems().stream().filter(item -> item != null && item.getId().equals(id)).findFirst().get();
    }

    @Override
    public void close() throws SQLException {
        this.conn.close();
    }

    private void createItemsIfNotExists() throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(IDS)) {
            try (ResultSet rs = st.executeQuery()) {
                rs.next();
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
                try (Statement statement = conn.createStatement()) {
                    statement.executeQuery(CREATE_ITEMS);
                }
            }
        }
    }
}
