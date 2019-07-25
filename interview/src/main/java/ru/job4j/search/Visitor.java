package ru.job4j.search;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.BlockingQueue;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Visitor extends SimpleFileVisitor<Path> {
    private static final Logger LOG = LogManager.getLogger(Visitor.class.getName());
    private final BlockingQueue<Path> paths;

    public Visitor(BlockingQueue<Path> paths) {
        this.paths = paths;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        try {
            paths.put(file);
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return CONTINUE;
    }
}
