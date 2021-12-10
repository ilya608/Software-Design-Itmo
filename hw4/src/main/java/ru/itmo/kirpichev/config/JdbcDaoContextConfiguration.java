package ru.itmo.kirpichev.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itmo.kirpichev.dao.TasksJdbcDao;

/**
 * @author ilyakirpichev
 */
@Configuration
public class JdbcDaoContextConfiguration {
    @Bean
    public TasksJdbcDao tasksJdbcDao(DataSource dataSource) {
        return new TasksJdbcDao(dataSource);
    }

    @Bean
    public DataSource dataSource() throws SQLException {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.sqlite.JDBC");
        dataSource.setUrl("jdbc:sqlite:product.db");
        dataSource.setUsername("");
        dataSource.setPassword("");
        String sql = "CREATE TABLE IF NOT EXISTS taskslist" +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                " NAME           TEXT    NOT NULL, " +
                " TASKS          TEXT     NOT NULL)";
//        dataSource.setSchema(sql);
        String dropSql = "DROP * FROM taskslist";
        dataSource.getConnection().createStatement().executeUpdate(sql);
//        dataSource.getConnection().createStatement().executeUpdate(dropSql);
        return dataSource;
    }
}
