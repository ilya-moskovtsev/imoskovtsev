package ru.job4j.io;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnalyzeTest {

    @Test
    public void unavailable() {
        File log = new File(this.getClass().getResource("/server.log").getFile());

        Analyze analyze = new Analyze();
        analyze.unavailable(log.getAbsolutePath(), "unavailable.csv");

        try (BufferedReader read = new BufferedReader(new FileReader("unavailable.csv"))) {
            assertThat(read.readLine(), is("10:57:01;10:59:01"));
            assertThat(read.readLine(), is("11:01:02;11:02:02"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}