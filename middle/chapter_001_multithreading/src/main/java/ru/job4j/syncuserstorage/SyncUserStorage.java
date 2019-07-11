package ru.job4j.syncuserstorage;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ThreadSafe
public class SyncUserStorage {
    public static class User {
        private int id;
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }

    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        synchronized (this) {
            return users;
        }
    }

    public boolean add(User user) {
        synchronized (this) {
            boolean result = false;
            User alreadyExists = users.stream().filter(u -> user.getId() == u.getId()).findFirst().orElse(null);
            if (Objects.isNull(alreadyExists)) {
                result = this.users.add(user);
            }
            return result;
        }
    }

    public boolean update(User user) {
        synchronized (this) {
            boolean result = false;
            boolean removed = this.users.removeIf(u -> u.getId() == user.getId());
            if (removed) {
                this.users.add(user);
                result = true;
            }
            return result;
        }
    }

    public boolean delete(User user) {
        synchronized (this) {
            return this.users.removeIf(u -> u.getId() == user.getId());
        }
    }

    public boolean transfer(int fromId, int toId, int amount) {
        synchronized (this) {
            boolean result = false;
            var from = users.stream().filter(u -> fromId == u.getId()).findFirst().orElse(null);
            var to = users.stream().filter(u -> toId == u.getId()).findFirst().orElse(null);
            if (Objects.nonNull(from) && Objects.nonNull(to)) {
                from.setAmount(from.getAmount() - amount);
                to.setAmount(to.getAmount() + amount);
                result = true;
            }
            return result;
        }
    }
}
