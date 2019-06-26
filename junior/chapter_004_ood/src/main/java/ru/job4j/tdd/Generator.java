package ru.job4j.tdd;

import java.util.Map;

public interface Generator {
    String replace(String string, Map<String, String> map) throws Exception;
}
