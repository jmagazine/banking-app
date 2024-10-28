package com.example.banking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Class for testing that database connections are successful.
 */
@SpringBootTest(classes = BankingApplication.class)
public class DatabaseConnectionTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            // If no exception is thrown, the connection is successful
        }
    }
}
