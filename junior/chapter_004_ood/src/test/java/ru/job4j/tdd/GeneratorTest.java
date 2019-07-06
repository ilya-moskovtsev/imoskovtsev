package ru.job4j.tdd;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GeneratorTest {

    @Test
    public void whenDifferentKeysThenDifferentValues() throws Exception {
        // Arrange
        Generator generator = new SimpleGenerator();
        String text = "Hello, ${name1}. I'm ${name2}.";
        Map<String, String> map = Map.of(
                "name1", "Nick",
                "name2", "Bill"
        );
        String expected = "Hello, Nick. I'm Bill.";
        // Act
        String result = generator.replace(text, map);
        // Assert
        assertThat(result, is(expected));
    }

    @Test
    public void whenSameKeysThenSameValues() throws Exception {
        // Arrange
        Generator generator = new SimpleGenerator();
        String text = "Help, ${sos}, ${sos}, ${sos}";
        Map<String, String> map = Map.of("sos", "Aaa");
        String expected = "Help, Aaa, Aaa, Aaa";
        // Act
        String result = generator.replace(text, map);
        // Assert
        assertThat(result, is(expected));
    }

    @Test
    public void whenGroupsAreNotInMapThenException() {
        // Arrange
        Generator generator = new SimpleGenerator();
        String text = "Help, ${key1}, ${key2}, ${key3}";
        Map<String, String> map = Map.of(
                "key1", "value1",
                "key2", "value2"
        );

        Exception thrown =
                // Assert
                assertThrows(Exception.class,
                        () -> generator.replace(text, map) // Act
                );
        // Assert
        assertThat(thrown.getMessage(), is("String has groups [key3] that are not in map"));
    }

    @Test
    public void whenMapHasExtraKeysThenException() {
        // Arrange
        Generator generator = new SimpleGenerator();
        String text = "Help, ${key1}.";
        Map<String, String> map = Map.of(
                "key1", "value1",
                "key2", "value2"
        );

        Exception thrown =
                // Assert
                assertThrows(Exception.class,
                        () -> generator.replace(text, map) // Act
                );
        // Assert
        assertThat(thrown.getMessage(), is("Map has keys [key2] that are not in string"));
    }
}