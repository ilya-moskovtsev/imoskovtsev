package ru.job4j.servlets.crud;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.sqlite.Config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DbStore implements Store {
    private static final Logger LOG = LogManager.getLogger(DbStore.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    // queries
    public static final String CREATE_USERS = "create table users(name varchar(200), login varchar(200) unique, email varchar(200) unique, create_date varchar(200))";
    public static final String IDS = "select ROWID from users";
    public static final String FIND_ALL = "SELECT t.*, ROWID FROM users t";
    // prepared statements
    public static final String ADD_USER = "insert into users(name, login, email, create_date) values (?, ?, ?, ?)";
    public static final String UPDATE_USER = "update users set name = ?, login = ?, email = ? where ROWID = ?";
    public static final String DELETE = "delete from users where ROWID = ?";
    public static final String FIND_BY_ID = "SELECT t.*, ROWID FROM users t where ROWID = ?";
    // column label
    public static final String ID = "ROWID";
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
        createUsersTableIfNotExists();
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) throws SQLException {
        try (final PreparedStatement st = SOURCE.getConnection().prepareStatement(ADD_USER)) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setString(4, String.valueOf(LocalDate.now()));
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void update(User user) throws SQLException {
        try (final PreparedStatement st = SOURCE.getConnection().prepareStatement(UPDATE_USER)) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getEmail());
            st.setInt(4, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void delete(User user) {
        try (final PreparedStatement st = SOURCE.getConnection().prepareStatement(DELETE)) {
            st.setInt(1, user.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new LinkedList<>();
        try (final Statement st = SOURCE.getConnection().createStatement();
             ResultSet resultSet = st.executeQuery(FIND_ALL)) {
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
        User user = new User();
        try (final PreparedStatement st = SOURCE.getConnection().prepareStatement(FIND_BY_ID)) {
            st.setInt(1, id);
            ResultSet resultSet = st.executeQuery();
            resultSet.next();
            user.setId(resultSet.getInt(ID));
            user.setName(resultSet.getString(NAME));
            user.setLogin(resultSet.getString(LOGIN));
            user.setEmail(resultSet.getString(EMAIL));
            user.setDateCreated(LocalDate.parse(resultSet.getString(CREATE_DATE)));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public List<Path> getFiles() {
        List<Path> files = null;
        Path uploads = Paths.get(System.getProperty("java.io.tmpdir"), "uploads");
        try (Stream<Path> list = Files.list(uploads)) {
            files = list.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    private void createUsersTableIfNotExists() {
        try (PreparedStatement st = SOURCE.getConnection().prepareStatement(IDS);
             ResultSet rs = st.executeQuery()) {
            rs.next();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try (Statement statement = SOURCE.getConnection().createStatement()) {
                statement.executeUpdate(CREATE_USERS);
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
    }
}
