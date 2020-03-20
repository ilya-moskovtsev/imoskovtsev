package ru.job4j.search;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class PathConsumerTest {
    public static final String RESOURCE_NAME = "/ru/job4j/search/someText.txt";

    @Test
    public void run() throws InterruptedException {
        int capacity = 2;
        BlockingQueue<Path> queue = new LinkedBlockingQueue<>(capacity);
        Path poisonPill = Paths.get("I", "am", "a", "poison", "pill");
        String textToSearch = "text";
        Path path = Paths.get(this.getClass().getResource(RESOURCE_NAME).getPath());

        queue.put(path);
        queue.put(poisonPill);

        PathConsumer consumer = new PathConsumer(queue, poisonPill, textToSearch);
        consumer.run();

        assertThat(queue.size(), is(0));
    }
}