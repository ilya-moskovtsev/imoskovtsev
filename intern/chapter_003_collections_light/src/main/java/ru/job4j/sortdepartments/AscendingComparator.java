package ru.job4j.sortdepartments;

import java.util.Comparator;

public class AscendingComparator implements Comparator<Department> {
    @Override
    public int compare(Department o1, Department o2) {
        int result = 0;
        final int lim = Math.min(o1.getSubdivisions().size(), o2.getSubdivisions().size());
        for (int i = 0; i < lim; i++) {
            final int subdivisionResult = o1.getSubdivisions().get(i).compareTo(o2.getSubdivisions().get(i));
            if (subdivisionResult != 0) {
                result = subdivisionResult;
                break;
            }
        }
        if (result == 0) {
            result = Integer.compare(o1.getSubdivisions().size(), o2.getSubdivisions().size());
        }
        return result;
    }
}
