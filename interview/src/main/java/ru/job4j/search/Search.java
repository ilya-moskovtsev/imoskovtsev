package ru.job4j.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.*;

public class Search {
    private static final Logger LOG = LogManager.getLogger(Search.class.getName());
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        Args argz = new Args(args);

        Path start = Paths.get(argz.getDirectory());
        int capacity = (int) (N_CONSUMERS * 1.5); // throttling
        BlockingQueue<Path> queue = new LinkedBlockingQueue<>(capacity);
        Path poisonPill = Paths.get("I", "am", "a", "poison", "pill");

        ExecutorService executor = Executors.newFixedThreadPool(N_CONSUMERS + 1);

        executor.submit(new PathProducer(queue, start, poisonPill, N_CONSUMERS));
        for (int i = 0; i < N_CONSUMERS; i++) {
            executor.submit(new PathConsumer(queue, poisonPill, argz.getText()));
        }
    }
}
