package ru.job4j.list2list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertList2List {
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        list.forEach(ints -> Arrays.stream(ints).forEach(result::add));

        return result;
    }
}
