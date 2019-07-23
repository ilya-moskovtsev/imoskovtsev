package ru.job4j.search;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PathProducerTest {
    public static final String RESOURCE_NAME = "/ru/job4j/search/dir";
    public static final int CAPACITY = 4;

    @Test
    public void run() {
        BlockingQueue<Path> queue = new LinkedBlockingQueue<>(CAPACITY);
        Path poisonPill = Paths.get("I", "am", "a", "poison", "pill");
        String dir = this.getClass().getResource(RESOURCE_NAME).getFile();
        Path start = Paths.get(dir);

        PathProducer producer = new PathProducer(queue, start, poisonPill, 1);
        producer.run();

        assertThat(queue.size(), is(CAPACITY));
    }
}