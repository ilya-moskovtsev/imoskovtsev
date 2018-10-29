package ru.job4j.stringcomparator;

import java.util.Comparator;

public class StringComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int result = 0;
        final int lim = Math.min(o1.length(), o2.length());
        for (int i = 0; i < lim; i++) {
            final int charResult = Character.compare(o1.charAt(i), o2.charAt(i));
            if (charResult != 0) {
                result = charResult;
                break;
            }
        }
        if (result == 0) {
            result = Integer.compare(o1.length(), o2.length());
        }
        return result;
    }
}
