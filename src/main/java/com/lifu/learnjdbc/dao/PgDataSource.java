package com.lifu.learnjdbc.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.SQLException;

public class PgDataSource {
    @Value("${postgres.connection.url}")
    private static String url;
    @Value("${postgres.connection.user}")
    private static String user;
    @Value("${postgres.connection.password}")
    private static String password;

    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(url);
        config.setPassword(password);
        config.setMaximumPoolSize(10); // Adjust according to your production needs
        config.setConnectionTimeout(30000); // 30 seconds
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Handle or log the exception
            }
        }
    }
}
