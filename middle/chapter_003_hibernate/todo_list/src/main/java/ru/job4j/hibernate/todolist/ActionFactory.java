package ru.job4j.hibernate.todolist;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ActionFactory {
    private static Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("getAll", new GetAllAction());
        actions.put("add", new AddAction());
        actions.put("done", new DoneAction());
    }

    public static Optional<Action> getAction(String action) {
        return Optional.ofNullable(actions.get(action));
    }
}
