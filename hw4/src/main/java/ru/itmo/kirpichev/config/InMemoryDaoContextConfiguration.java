package ru.itmo.kirpichev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.kirpichev.dao.TasksDao;
import ru.itmo.kirpichev.dao.TasksInMemoryDao;

/**
 * @author ilyakirpichev
 */
@Configuration
public class InMemoryDaoContextConfiguration {
    @Bean
    public TasksDao tasksDao() {
        return new TasksInMemoryDao();
    }
}
