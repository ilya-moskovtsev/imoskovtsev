package ru.job4j.array2list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertMatrix2ListTest {

    @Test
    public void toList() {
        ConvertMatrix2List convertMatrix2List = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = List.of(1, 2, 3, 4);
        List<Integer> result = convertMatrix2List.toList(input);
        assertThat(result, is(expect));
    }
}