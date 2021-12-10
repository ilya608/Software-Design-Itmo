package ru.itmo.kirpichev.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ilyakirpichev
 */
public class TaskList {
    public List<Task> tasks;
    public int position;
    public String name;

    public TaskList() {
    }

    public TaskList(String name) {
        this.name = name;
        tasks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }


    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
