package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * Loads key=value pairs from file to map.
     * .properties file may contain empty lines and comments.
     * <p>
     * To read files you need to use:
     * <p>
     * import java.io.BufferedReader;
     * import java.io.FileReader;
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Predicate<String> isCommented = s -> s.startsWith("#");
            Predicate<String> isEmpty = String::isEmpty;
            values.putAll(
                    read.lines().filter(isCommented.negate().and(isEmpty.negate()))
                            .map(s -> s.split("="))
                            .collect(Collectors.toMap(s -> s[0], s -> s[1]))
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
