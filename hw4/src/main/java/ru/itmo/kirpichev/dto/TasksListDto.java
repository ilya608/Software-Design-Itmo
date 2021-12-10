package ru.itmo.kirpichev.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author ilyakirpichev
 */
public class TasksListDto {

//    @JsonProperty("id")
//    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("tasks")
    public List<TaskDto> tasks;

    public TasksListDto(
            @JsonProperty("name") String name,
            @JsonProperty("tasks") List<TaskDto> tasks)
    {
//        this.id = id;
        this.name = name;
        this.tasks = tasks;
    }

    class TaskDto {
        @JsonProperty("name")
        public String name;
        @JsonProperty("is_done")
        public boolean isDone;
    }
}
