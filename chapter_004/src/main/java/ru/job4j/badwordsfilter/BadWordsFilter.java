package ru.job4j.badwordsfilter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BadWordsFilter {

    Set<String> badWords = new HashSet<>();

    public void setBadWords(Set<String> badWords) {
        this.badWords = badWords;
    }

    /**
     * Replaces bad words with *.
     *
     * @param strings to be censored
     * @return a list of censored strings
     */
    public List<String> filterBadWords(List<String> strings) {
        return strings.stream()
                .map(str -> badWords.stream()
                        .reduce(str, (left, right) -> left.replaceAll(right, "*".repeat(right.length())))
                )
                .collect(Collectors.toList());
    }
}
