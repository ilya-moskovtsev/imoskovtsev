package ru.job4j.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Chat {
    public static final String REPLIES_TXT = "replies.txt";
    public static final String LOG_TXT = "log.txt";
    public static final String PAUSE = "pause";
    public static final String CONTINUE = "continue";
    public static final String EXIT = "exit";
    public List<String> lines;

    public static void main(String[] args) {
        Chat chat = new Chat();
        chat.loadReplies(new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(REPLIES_TXT)).getFile()));
        chat.start();
    }

    private void start() {
        try (PrintWriter log = new PrintWriter(new FileOutputStream(LOG_TXT))) {
            try (BufferedReader read = new BufferedReader(new InputStreamReader(System.in))) {
                boolean shouldExit = false;
                boolean isPaused = false;
                Random random = new Random();
                while (!shouldExit) {
                    String user = read.readLine();
                    log.println(user);
                    switch (user) {
                        case PAUSE:
                            isPaused = true;
                            break;
                        case CONTINUE:
                            isPaused = false;
                            break;
                        case EXIT:
                            shouldExit = true;
                            break;
                        default:
                            if (!isPaused) {
                                String bot = this.lines.get(random.nextInt(this.lines.size()));
                                log.println(bot);
                                System.out.println(bot);
                            }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadReplies(File replies) {
        try (BufferedReader read = new BufferedReader(new FileReader(replies.getAbsolutePath()))) {
            lines = new ArrayList<>();
            read.lines().forEach(lines::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
