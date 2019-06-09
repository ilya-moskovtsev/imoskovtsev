package ru.job4j.tracker;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Used in integration tests.
 * autocommit=false
 * rollbacks changes on close
 */
public class ConnectionRollback {
    public static final String CLOSE = "close";

    public static Connection create(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        return (Connection) Proxy.newProxyInstance(
                ConnectionRollback.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    Object rs = null;
                    if (CLOSE.equals(method.getName())) {
                        conn.rollback();
                        conn.close();
                    } else {
                        rs = method.invoke(conn, args);
                    }
                    return rs;
                }
        );
    }
}
