package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for a jagged two-dimensional array of int.
 */
public class JaggedArrayIterator implements Iterator {

    private int[][] jaggedArray;

    private int indexI = 0;
    private int indexJ = 0;

    public JaggedArrayIterator(int[][] jaggedArray) {
        this.jaggedArray = jaggedArray;
    }

    @Override
    public boolean hasNext() {
        return jaggedArray.length > indexI
                && jaggedArray[indexI].length > indexJ
                || jaggedArray.length > indexI + 1
                && jaggedArray[indexI + 1].length > 0;
    }

    @Override
    public Object next() throws NoSuchElementException {
        if (jaggedArray[indexI].length <= indexJ) {
            indexI++;
            indexJ = 0;
        }
        if (hasNext()) {
            return jaggedArray[indexI][indexJ++];
        } else {
            throw new NoSuchElementException();
        }
    }
}
