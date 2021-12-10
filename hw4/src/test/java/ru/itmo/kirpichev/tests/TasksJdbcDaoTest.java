package ru.itmo.kirpichev.tests;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.itmo.kirpichev.Application;
import ru.itmo.kirpichev.dao.TasksJdbcDao;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author
 */
@SpringBootTest
class TasksJdbcDaoTest {
    @Autowired
    TasksJdbcDao dao;

    @Test
    public void test() {
        int a = 3;
    }
}