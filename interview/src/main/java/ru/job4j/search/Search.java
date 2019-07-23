package ru.job4j.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Search {
    private static final Logger LOG = LogManager.getLogger(Search.class.getName());
    private static final int N_CONSUMERS = Runtime.getRuntime().availableProcessors();

    public static void main(String[] args) {
        Args argz = new Args(args);
        LOG.info(String.format("Will search for text %s in the following directory %s", argz.getText(), argz.getDirectory()));
        Path start = Paths.get(argz.getDirectory());
        BlockingQueue<Path> queue = new LinkedBlockingQueue<>();
        Path poisonPill = Paths.get("I", "am", "a", "poison", "pill");

        ExecutorService executor = Executors.newFixedThreadPool(N_CONSUMERS + 1);

        executor.submit(new PathProducer(queue, start, poisonPill, N_CONSUMERS));
        for (int i = 0; i < N_CONSUMERS; i++) {
            executor.submit(new PathConsumer(queue, poisonPill, argz.getText()));
        }

        shutDown(executor);
        LOG.info("Job's done");
    }

    private static void shutDown(ExecutorService executor) {
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
