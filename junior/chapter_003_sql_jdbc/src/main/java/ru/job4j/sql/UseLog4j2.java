package ru.job4j.sql;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UseLog4j2 {
    private static final Logger LOG = LogManager.getLogger(UseLog4j2.class.getName());

    public static void main(String[] args) {
        int version = 1;
        LOG.trace("trace message {}", version);
        LOG.debug("debug message {}", version);
        LOG.info("info message {}", version);
        LOG.warn("warn message {}", version);
        LOG.error("error message {}", version);
    }
}
