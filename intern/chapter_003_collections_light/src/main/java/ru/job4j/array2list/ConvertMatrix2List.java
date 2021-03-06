package ru.job4j.array2list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertMatrix2List {
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        Arrays.stream(array).forEach(i -> Arrays.stream(i).forEach(list::add));
        return list;
    }
}
