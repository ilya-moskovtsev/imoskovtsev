package ru.job4j.io;

import java.io.File;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Search {
    /**
     * Searches for files that have required extensions.
     *
     * @param parent     path to the directory from which to start the search
     * @param extensions extensions of files to find
     * @return list of files that have required extensions
     */
    public List<File> filesByExtensions(String parent, List<String> extensions) {
        List<File> allFiles = new LinkedList<>();
//        listFilesRecursively(allFiles, new File(parent));
        listFilesUsingStack(allFiles, new File(parent));

        String regExp = getRegExp(extensions);
        return filterByRegExp(allFiles, regExp);
    }

    /**
     * Lists recursively directory tree.
     *
     * @param result list of files
     * @param dir    to start listing from
     */
    private void listFilesRecursively(List<File> result, File dir) {
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            result.add(file);
            if (file.isDirectory()) {
                listFilesRecursively(result, file);
            }
        }
    }

    /**
     * Lists directory tree using stack.
     *
     * @param result list of filesByExtensions
     * @param dir    to start listing from
     */
    private void listFilesUsingStack(List<File> result, File dir) {
        Deque<File> stack = new LinkedList<>();
        stack.offerFirst(dir);
        while (!stack.isEmpty()) {
            File[] files = Objects.requireNonNull(stack.pollFirst()).listFiles();
            if (files != null && files.length != 0) {
                for (File file : files) {
                    result.add(file);
                    if (file.isDirectory()) {
                        stack.offerFirst(file);
                    }
                }
            }
        }
    }

    /**
     * Builds regexp from list of extensions.
     *
     * @param extensions to build regexp from
     * @return regexp
     */
    private String getRegExp(List<String> extensions) {
        StringJoiner joiner = new StringJoiner("|\\.", ".*(\\.", ")$");
        extensions.forEach(joiner::add);
        return joiner.toString();
    }

    private List<File> filterByRegExp(List<File> allFiles, String regExp) {
        return allFiles.stream().filter(file -> file.getName().matches(regExp))
                .collect(Collectors.toList());
    }
}
