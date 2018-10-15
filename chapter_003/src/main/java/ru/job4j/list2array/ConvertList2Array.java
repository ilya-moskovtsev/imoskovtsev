package ru.job4j.list2array;

import java.util.Iterator;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {

        int cells = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[rows][cells];
        Iterator<Integer> iterator = list.iterator();

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (iterator.hasNext()) {
                    array[i][j] = iterator.next();
                } else {
                    break;
                }
            }
        }

        return array;
    }
}
