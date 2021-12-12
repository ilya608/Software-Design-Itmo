package ru.itmo.kirpichev.dao;

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.itmo.kirpichev.model.Task;
import ru.itmo.kirpichev.model.TaskList;

/**
 * @author ilyakirpichev
 */
public interface TasksDao {
    void addTask(int listId, Task task);
    void addEmptyTaskList(String taskList) throws RuntimeException;
    List<TaskList> getLists() throws SQLException;
    TaskList getTasksByListId(int id);
    boolean deleteTaskListById(int id);
    void setTaskDone(int listId, int taskId);
}
