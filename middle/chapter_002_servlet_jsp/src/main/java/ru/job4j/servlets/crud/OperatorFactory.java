package ru.job4j.servlets.crud;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {
    private static Map<String, Operation> operationMap = new HashMap<>();

    static {
        operationMap.put("add", new AddOperation());
        operationMap.put("update", new UpdateOperation());
        operationMap.put("delete", new DeleteOperation());
    }

    public static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}