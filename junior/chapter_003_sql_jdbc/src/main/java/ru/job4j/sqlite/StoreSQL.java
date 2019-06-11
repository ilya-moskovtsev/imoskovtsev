package ru.job4j.sqlite;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Generates and gets data from SQLite.
 */
public class StoreSQL implements AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    private final Config config;
    private Connection conn;
    // properties
    public static final String URL = "url";
    // queries
    public static final String GET_ENTRIES = "select * from entries";
    public static final String IDS = "select rowId from entries";
    private static final String CREATE_ENTRIES = "create table entries(field integer)";
    // prepared statements
    private static final String INSERT = "insert into entries (field) values (?)";
    // column label
    public static final String FIELD = "field";

    public StoreSQL(Config config) {
        this.config = config;
    }

    public StoreSQL init() {
        try {
            this.conn = DriverManager.getConnection(config.get(URL));
            createEntriesIfNotExists();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this;
    }

    /**
     * Inserts n fields into entries table.
     *
     * @param n - number of fields
     * @return this
     */
    public StoreSQL generate(int n) {
        LOG.info(String.format("Will insert %d fields into entries table", n));
        try (final PreparedStatement st = conn.prepareStatement(INSERT)) {
            conn.setAutoCommit(false);
            for (int i = 0; i < n; i++) {
                st.setInt(1, i);
                st.addBatch();
            }
            st.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this;
    }

    public List<StoreXML.Entry> getEntries() {
        LOG.info(String.format("Will get entries from %s", config.get(URL)));
        List<StoreXML.Entry> entries = new LinkedList<>();
        try (final Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(GET_ENTRIES)) {
            while (resultSet.next()) {
                entries.add(new StoreXML.Entry(resultSet.getInt(FIELD)));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info(String.format("Found %s entries", entries.size()));
        return entries;
    }

    @Override
    public void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    private void createEntriesIfNotExists() {
        try (PreparedStatement st = conn.prepareStatement(IDS);
             ResultSet rs = st.executeQuery()) {
            rs.next();
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            try (Statement statement = conn.createStatement()) {
                LOG.info("Will create entries table");
                statement.executeUpdate(CREATE_ENTRIES);
            } catch (Exception e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }
}
