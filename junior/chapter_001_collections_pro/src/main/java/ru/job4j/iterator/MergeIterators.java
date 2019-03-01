package ru.job4j.iterator;

import java.util.Iterator;

public class MergeIterators {
    /**
     * Merges iterator of iterators into one iterator.
     *
     * @param iterators Iterator over Iterators that iterate over Integers
     * @return Iterator over Integers
     */
    public Iterator<Integer> mergeIterators(Iterator<Iterator<Integer>> iterators) {
        return new Iterator<>() {
            private Iterator<Integer> currentIterator = iterators.next();

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (currentIterator.hasNext()) {
                    result = true;
                } else if (iterators.hasNext()) {
                    currentIterator = iterators.next();
                    result = currentIterator.hasNext();
                }

                return result;
            }

            @Override
            public Integer next() {
                if (!currentIterator.hasNext() && iterators.hasNext()) {
                    currentIterator = iterators.next();
                }

                return currentIterator.next();
            }
        };
    }
}