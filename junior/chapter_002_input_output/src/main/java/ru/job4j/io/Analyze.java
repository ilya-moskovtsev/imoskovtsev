package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Analyze {
    private static final String OK = "200";
    private static final String BAD_REQUEST = "400";
    private static final String INTERNAL_SERVER_ERROR = "500";
    private static final String DELIMITER = ";";
    private boolean isAvailable = true;

    public void unavailable(String source, String target) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            read.lines().forEach(s -> {
                if (isAvailable && (s.startsWith(BAD_REQUEST) || s.startsWith(INTERNAL_SERVER_ERROR))) {
                    lines.add(s.substring(4));
                    isAvailable = false;
                }
                if (!isAvailable && s.startsWith(OK)) {
                    lines.add(s.substring(4));
                    isAvailable = true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            for (int i = 0; i < lines.size(); i += 2) {
                out.println(String.join(DELIMITER, lines.subList(i, i + 2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
