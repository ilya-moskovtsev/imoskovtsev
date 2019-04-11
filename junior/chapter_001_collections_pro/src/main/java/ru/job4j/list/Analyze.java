package ru.job4j.list;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analyze {
    public Info diff(List<User> previous, List<User> current) {
        var info = new Info();
        var set = new HashSet<>(previous);
        var map = current.stream().collect(Collectors.toMap(u -> u, u -> u));
        for (var user : set) {
            if (!map.containsKey(user)) {
                info.deleted++;
            } else {
                if (!Objects.equals(user.name, map.get(user).name)) {
                    info.changed++;
                }
            }
        }
        int size = set.size();
        set.addAll(current);
        info.added = set.size() - size;
        return info;
    }

    public static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
