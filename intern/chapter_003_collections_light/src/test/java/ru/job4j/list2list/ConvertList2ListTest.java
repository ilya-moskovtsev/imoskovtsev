package ru.job4j.list2list;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ListTest {

    @Test
    public void convert() {
        List<int[]> input = List.of(new int[]{1, 2}, new int[]{3, 4, 5, 6});

        List<Integer> expected = List.of(1, 2, 3, 4, 5, 6);

        ConvertList2List convertList2List = new ConvertList2List();
        List<Integer> result = convertList2List.convert(input);

        assertThat(result, is(expected));
    }
}