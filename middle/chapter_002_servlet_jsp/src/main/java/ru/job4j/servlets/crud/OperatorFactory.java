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
        operationMap.put("upload", new UploadOperation());
        operationMap.put("login", new LoginOperation());
        operationMap.put("logout", new LogoutOperation());
    }

    public static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}
