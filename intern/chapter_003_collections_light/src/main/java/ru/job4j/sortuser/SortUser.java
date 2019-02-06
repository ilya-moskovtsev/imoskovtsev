package ru.job4j.sortuser;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.Comparator;

public class SortUser {
    public Set<User> sort(List<User> list) {
        return new TreeSet<>(list);
    }

    public List<User> sortByNameLength(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().length() - o2.getName().length();
            }
        });
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                final int name = o1.getName().compareTo(o2.getName());
                return name != 0 ? name : o1.getAge().compareTo(o2.getAge());
            }
        });
        return list;
    }
}
