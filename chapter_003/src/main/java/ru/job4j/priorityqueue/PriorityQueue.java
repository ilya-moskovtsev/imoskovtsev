package ru.job4j.priorityqueue;

import java.util.LinkedList;

public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Inserts a task in the tasks list at the position defined by the priority of a task.
     *
     * @param task task to insert
     */
    public void put(Task task) {
        int index = tasks.size();
        for (int i = 0; i < tasks.size(); i++) {
            if (task.getPriority() <= tasks.get(i).getPriority()) {
                index = i;
                break;
            }
        }
        tasks.add(index, task);
    }

    public Task take() {
        return this.tasks.poll();
    }

}
