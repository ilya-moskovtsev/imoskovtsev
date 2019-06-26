package ru.job4j.tdd;

import com.google.common.base.Joiner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Generator {
    // strings
    private static final String START = "${";
    private static final String EMPTY = "";
    private static final String FINISH = "}";
    public static final String SPACE = " ";
    public static final String STRING_HAS_GROUPS = "String has groups";
    public static final String THAT_ARE_NOT_IN_MAP = "that are not in map";
    public static final String MAP_HAS_KEYS = "Map has keys";
    public static final String THAT_ARE_NOT_IN_STRING = "that are not in string";
    // pattern
    private static final Pattern GROUPS = Pattern.compile("\\Q${\\E[^\\Q}\\E]+\\Q}\\E");

    @Override
    public String replace(String string, Map<String, String> map) throws Exception {
        Matcher matcher = GROUPS.matcher(string);
        Set<String> groups = new HashSet<>();
        while (matcher.find()) {
            String group = matcher.group();
            String key = group.replace(START, EMPTY).replace(FINISH, EMPTY);
            String quote = Pattern.quote(group);
            String replacement = map.get(key) == null ? EMPTY : map.get(key);
            string = string.replaceAll(quote, replacement);
            groups.add(key);
        }
        Set<String> keys = new HashSet<>(map.keySet());

        checks(groups, keys);

        return string;
    }

    private void checks(Set<String> groups, Set<String> keys) throws Exception {
        Set<String> groupsCopy = new HashSet<>(groups);
        groups.removeAll(keys);
        if (groups.size() > 0) {
            throw new Exception(Joiner.on(SPACE).join(STRING_HAS_GROUPS, groups, THAT_ARE_NOT_IN_MAP));
        }
        keys.removeAll(groupsCopy);
        if (keys.size() > 0) {
            throw new Exception(Joiner.on(SPACE).join(MAP_HAS_KEYS, keys, THAT_ARE_NOT_IN_STRING));
        }
    }
}
