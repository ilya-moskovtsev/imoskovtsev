package ru.job4j.gc;

import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GarbageCollectorDemo {
    private static final Logger LOG = LogManager.getLogger(GarbageCollectorDemo.class.getName());
    private static final String SPACE = " ";
    public static final String VM_OPTIONS_XMX_8_M = "VM options: -Xmx8m";
    public static final String TOTAL_MEMORY = "totalMemory";
    public static final String BYTES = "bytes";
    public static final String NAME = "Name";
    public static final String USERS_TOOK = "Users took";
    public static final String NO_FIELDS_TOOK = "NoFields took";
    public static final String FREE_MEMORY = "freeMemory";
    public static final int N = 1000;

    private static class User {
        private static final String CREATED_USER = "created User";
        private static final String FINALIZED_USER = "finalized User";
        private String name;

        public User(String name) {
            this.name = name;
            LOG.info(Joiner.on(SPACE).join(CREATED_USER, name));
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            LOG.info(Joiner.on(SPACE).join(FINALIZED_USER, name));
        }
    }

    private static class NoFields {
        public NoFields() {
        }
    }

    public static void main(String[] args) {
        LOG.info(VM_OPTIONS_XMX_8_M);
        Runtime runtime = Runtime.getRuntime();
        LOG.info(Joiner.on(SPACE).join(TOTAL_MEMORY, runtime.totalMemory(), BYTES));

        long before = runtime.freeMemory();
        for (int i = 0; i < N; i++) {
            new User(Joiner.on(SPACE).join(NAME, i));
        }
        long after = runtime.freeMemory();
        LOG.info(Joiner.on(SPACE).join(USERS_TOOK, before - after, BYTES));

        long before1 = runtime.freeMemory();
        for (int i = 0; i < N; i++) {
            new NoFields();
        }
        long after1 = runtime.freeMemory();
        LOG.info(Joiner.on(SPACE).join(N, NO_FIELDS_TOOK, before1 - after1, BYTES));

        LOG.info(Joiner.on(SPACE).join(N, FREE_MEMORY, runtime.freeMemory(), BYTES));
    }
}
