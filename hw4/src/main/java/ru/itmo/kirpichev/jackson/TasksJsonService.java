package ru.itmo.kirpichev.jackson;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itmo.kirpichev.dto.TasksListDto;

/**
 * @author ilyakirpichev
 */
public class TasksJsonService {
    private final ObjectMapper mapper = new ObjectMapper();

    public String addTask(String jsonTasks, String name) throws IOException {

        TasksListDto dto = mapper.readValue(jsonTasks, TasksListDto.class);
        dto.tasks.add(new TasksListDto.TaskDto(name, false));
        return mapper.writeValueAsString(dto);
    }
}
