package ru.itmo.kirpichev.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import ru.itmo.kirpichev.model.Task;
import ru.itmo.kirpichev.model.TaskList;

/**
 * @author ilyakirpichev
 */
public class TasksInMemoryDao implements TasksDao {
    private final AtomicInteger lastTaskId = new AtomicInteger(0);
    private final List<TaskList> taskLists = new ArrayList<>();

    @Override
    public void addTask(int listId, Task task) {
        task.id = lastTaskId.incrementAndGet();
        task.position = taskLists.get(listId).getTasks().size();
        taskLists.get(listId).addTask(task);
    }

    @Override
    public void addEmptyTaskList(String name) {
        taskLists.add(new TaskList(name));
    }

    @Override
    public List<TaskList> getLists() {
        var result = List.copyOf(taskLists);
        int position = 0;
        for (TaskList list : result) {
            list.position = position++;
        }
        return result;
    }

    @Override
    public TaskList getTasksByListId(int id) {
        return taskLists.get(id);
    }

    @Override
    public boolean deleteTaskListById(int id) {
        if (id >= taskLists.size()) {
            return false;
        }
        taskLists.remove(id);
        return true;
    }

    @Override
    public void setTaskDone(int listId, int taskId) {
        taskLists.get(listId).tasks.get(taskId).setDone(true);
    }
}
