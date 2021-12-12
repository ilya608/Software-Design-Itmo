package ru.itmo.kirpichev.jackson;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
class TasksJsonServiceTest {
    TasksJsonService jsonService = new TasksJsonService();
    @Test
    public void test() throws IOException {
        String json = "{\"name\": \"футбол\", \"tasks\": []}";
        String res = jsonService.addTask(json, "футбол");
        int a = 3;
    }
}