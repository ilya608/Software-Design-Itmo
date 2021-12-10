package ru.itmo.kirpichev.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kirpichev.dao.TasksDao;
import ru.itmo.kirpichev.dao.TasksJdbcDao;
import ru.itmo.kirpichev.model.Task;
import ru.itmo.kirpichev.model.TaskList;

/**
 * @author ilyakirpichev
 */
@Controller
public class TaskController {
    private final TasksDao tasksDao;

    public TaskController(TasksJdbcDao tasksJdbcDao) {
        this.tasksDao = tasksJdbcDao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main() {
        return "redirect:/get-tasks";
    }

    @RequestMapping(value = "/get-tasks", method = RequestMethod.GET)
    public String getTasks(ModelMap map) {
        prepareModelMap(map, tasksDao.getLists());
        return "index";
    }

    @RequestMapping(value = "/add-tasklist", method = RequestMethod.POST)
    public String addEmptyTaskList(@RequestParam("name") String name) {
        tasksDao.addEmptyTaskList(name);
        return "redirect:/";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(@RequestParam("position") int listId, @RequestParam("name") String name) {
        tasksDao.addTask(listId, new Task(name));
        return "redirect:/";
    }

    @RequestMapping(value = "/delete-tasklist/{listId}", method = RequestMethod.POST)
    public String deleteTaskList(@PathVariable int listId) {
        tasksDao.deleteTaskListById(listId);
        return "redirect:/";
    }

    @RequestMapping(value = "/set-done/{listId}/{taskId}", method = RequestMethod.POST)
    public String setTaskDone(@PathVariable int listId, @PathVariable int taskId) {
        tasksDao.setTaskDone(listId, taskId);
        return "redirect:/";
    }

    private void prepareModelMap(ModelMap map, List<TaskList> products) {
        map.addAttribute("tasks", products);
        map.addAttribute("taskList", new TaskList());

//        map.addAttribute("filter", new Filter());
    }
}
