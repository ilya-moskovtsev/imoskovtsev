package ru.job4j.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class PathConsumer implements Runnable {
    private static final Logger LOG = LogManager.getLogger(PathConsumer.class.getName());
    private BlockingQueue<Path> queue;
    private final Path poisonPill;
    private final String text;

    public PathConsumer(BlockingQueue<Path> queue, Path poisonPill, String text) {
        this.queue = queue;
        this.poisonPill = poisonPill;
        this.text = text;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Path path = queue.take();
                if (path.equals(poisonPill)) {
                    return;
                }
                try (BufferedReader reader = Files.newBufferedReader(path);) {
                    boolean match = reader.lines().anyMatch(line -> line.contains(text));
                    if (match) {
                        LOG.info(path);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}