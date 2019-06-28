package ru.job4j.isp;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

class MenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final Consumer<String> output = new Consumer<>() {
        private final PrintStream stdout = new PrintStream(outContent);

        @Override
        public void accept(String s) {
            stdout.println(s);
        }
    };

    @Test
    void print() {
        Menu menu = new Menu(
                List.of(
                        new MenuItem("task 1", "-", List.of(
                                new MenuItem("sub task 1.1", "-", null),
                                new MenuItem("sub task 1.2", "-", null)
                        )),
                        new MenuItem("task 2", "-", null),
                        new MenuItem("task 3", "-", List.of(
                                new MenuItem("sub task 3.1", "-", List.of(
                                        new MenuItem("sub task 3.1.1", "-", null)
                                ))
                        ))
                )
        );

        menu.print("-", output);

        assertThat(outContent.toString(),
                is(Joiner.on(System.lineSeparator()).join(
                        "- task 1",
                        "-- sub task 1.1",
                        "-- sub task 1.2",
                        "- task 2",
                        "- task 3",
                        "-- sub task 3.1",
                        "---- sub task 3.1.1",
                        "")
                )
        );
    }
}