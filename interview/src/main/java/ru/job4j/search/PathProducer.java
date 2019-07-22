package ru.job4j.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.BlockingQueue;

public class PathProducer implements Runnable {
    private static final Logger LOG = LogManager.getLogger(PathProducer.class.getName());
    private BlockingQueue<Path> queue;
    private final Path start;
    private final Path poisonPill;
    private final int poisonPillPerProducer;

    public PathProducer(BlockingQueue<Path> queue, Path start, Path poisonPill, int poisonPillPerProducer) {
        this.queue = queue;
        this.start = start;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    @Override
    public void run() {
        try {
            Files.walkFileTree(start, new Visitor(queue));
            for (int i = 0; i < poisonPillPerProducer; i++) {
                queue.put(poisonPill);
            }
        } catch (InterruptedException | IOException e) {
            LOG.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }
}