package ru.job4j.list2list;

import java.util.ArrayList;
import java.util.List;

public class ConvertList2List {
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] ints : list) {
            for (int i : ints) {
                result.add(i);
            }
        }

        return result;
    }
}
