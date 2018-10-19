package ru.job4j.list2map;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class UserConverter {
    public Map<Integer, User> process(List<User> list) {
        Map<Integer, User> users = new HashMap<>();
        for (User user : list) {
            users.put(user.getId(), user);
        }
        return users;
    }
}
