package ru.job4j.sqlruparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;

public class StoreSQL implements AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(StoreSQL.class.getName());
    public static final String WILL_CREATE_JOBS_TABLE = "Will create jobs table";

    private final Config config;
    private Connection conn;
    // properties
    public static final String URL = "url";
    // queries
    private static final String CREATE_TABLE_JOBS = "create table jobs(name text, text text, link text)";
    public static final String SELECT_ALL_FROM_JOBS = "select * from jobs";
    public static final String SELECT_ROW_ID_FROM_JOBS = "select rowId from jobs";
    private static final String CREATE_TABLE_LOGS = "create table logs(start_time text)";
    public static final String COUNT_LOGS = "select count(rowID) as count from logs";
    // prepared statements
    private static final String INSERT_INTO_JOBS = "insert into jobs (name, text, link) values (?, ?, ?)";
    public static final String INSERT_INTO_LOGS = "insert into logs (start_time) values (?)";
    public static final String SELECT_START_TIME = "select start_time from logs where rowID = (?)";
    // column labels
    public static final String NAME = "name";
    public static final String TEXT = "text";
    public static final String LINK = "link";
    public static final String START_TIME = "start_time";
    public static final String COUNT = "count";

    public StoreSQL(Config config) {
        this.config = config;
    }

    public StoreSQL init() {
        try {
            this.conn = DriverManager.getConnection(config.get(URL));
            createJobsTableIfNotExists();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this;
    }

    /**
     * @param thisStartTime this start time
     * @return last start time or null
     */
    public LocalDateTime logStart(LocalDateTime thisStartTime) {
        LocalDateTime lastStartTime = null;
        try (final Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery(COUNT_LOGS);
            int count = resultSet.getInt(COUNT);
            if (count == 0) {
                try (final PreparedStatement st1 = conn.prepareStatement(INSERT_INTO_LOGS)) {
                    st1.setString(1, String.valueOf(thisStartTime));
                    st1.executeUpdate();
                }
                lastStartTime = null;
            } else {
                try (final PreparedStatement st2 = conn.prepareStatement(SELECT_START_TIME)) {
                    st2.setInt(1, count);
                    ResultSet resultSet1 = st2.executeQuery();
                    String startTime = resultSet1.getString(START_TIME);
                    lastStartTime = LocalDateTime.parse(startTime);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return lastStartTime;
    }

    /**
     * Store javaJobs to database.
     *
     * @param javaJobs to be stored
     * @return
     */
    public StoreSQL storeJobs(LinkedHashSet<JavaJob> javaJobs) {
        try (final PreparedStatement st = conn.prepareStatement(INSERT_INTO_JOBS)) {
            conn.setAutoCommit(false);
            for (JavaJob javaJob : javaJobs) {
                st.setString(1, javaJob.getName());
                st.setString(2, javaJob.getText());
                st.setString(3, javaJob.getLink());
                st.addBatch();
            }
            st.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return this;
    }

    public LinkedHashSet<JavaJob> getJobs() {
        LinkedHashSet<JavaJob> javaJobs = new LinkedHashSet<>();
        try (final Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery(SELECT_ALL_FROM_JOBS)) {
            while (resultSet.next()) {
                javaJobs.add(
                        new JavaJob(
                                resultSet.getString(NAME),
                                resultSet.getString(TEXT),
                                resultSet.getString(LINK)
                        )
                );
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return javaJobs;
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        }
    }

    private void createJobsTableIfNotExists() {
        try (PreparedStatement st = conn.prepareStatement(SELECT_ROW_ID_FROM_JOBS);
             ResultSet rs = st.executeQuery()) {
            rs.next();
        } catch (Exception e) {
            LOG.warn(e.getMessage(), e);
            try (Statement statement = conn.createStatement()) {
                LOG.info(WILL_CREATE_JOBS_TABLE);
                statement.executeUpdate(CREATE_TABLE_JOBS);
                statement.executeUpdate(CREATE_TABLE_LOGS);
            } catch (Exception e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }
}
