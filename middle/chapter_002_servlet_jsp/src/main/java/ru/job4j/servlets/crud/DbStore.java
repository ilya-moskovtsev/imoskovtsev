package ru.job4j.servlets.crud;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import ru.job4j.sqlite.Config;


import org.apache.commons.dbcp2.BasicDataSource;

public class DbStore implements Store {
    private static final Logger LOG = LogManager.getLogger(DbStore.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();
    private Connection conn;

    // queries
    public static final String CREATE_USERS = "create table users(id serial primary key,name varchar(200),login varchar(200),email varchar(200),create_date varchar(200))";
    public static final String IDS = "select rowId from users";
    public static final String GET_USERS = "select * from users";
    // prepared statements
    public static final String ADD_USER = "insert into users(id, name, login, email, create_date) values (? ,? ,? ,? ,?)";
    public static final String UPDATE_USER = "update users set name = ?, login = ?, email = ? where id = ?";
    public static final String DELETE = "delete from users where id = ?";
    // column label
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String LOGIN = "login";
    public static final String EMAIL = "email";
    public static final String CREATE_DATE = "create_date";

    public DbStore() {
        Config config = new Config().init();
        SOURCE.setDriverClassName(config.get("driver-class-name"));
        SOURCE.setUrl(config.get("url"));
        SOURCE.setUsername(config.get("user"));
        SOURCE.setPassword(config.get("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try {
            this.conn = SOURCE.getConnection();
            createUsersTableIfNotExists();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (final PreparedStatement st = conn.prepareStatement(ADD_USER)) {
            st.setInt(1, user.getId());
            st.setString(2, user.getName());
            st.setString(3, user.getLogin());
            st.setString(4, user.getEmail());
            st.setString(5, String.valueOf(LocalDate.now()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(User user) {
        try (final PreparedStatement st = conn.prepareStatement(UPDATE_USER)) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(User user) {
        try (final PreparedStatement st = conn.prepareStatement(DELETE)) {
            st.setInt(1, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new LinkedList<>();
        try (final Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(GET_USERS)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(ID));
                user.setName(resultSet.getString(NAME));
                user.setLogin(resultSet.getString(LOGIN));
                user.setEmail(resultSet.getString(EMAIL));
                user.setDateCreated(LocalDate.parse(resultSet.getString(CREATE_DATE)));
                users.add(user);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User findById(int id) {
        return findAll().stream().filter(user -> user != null && user.getId() == id).findFirst().orElse(null);
    }

    private void createUsersTableIfNotExists() throws SQLException {
        try (PreparedStatement st = conn.prepareStatement(IDS);
             ResultSet rs = st.executeQuery()) {
            rs.next();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try (Statement statement = conn.createStatement()) {
                statement.executeUpdate(CREATE_USERS);
            }
        }
    }
}
