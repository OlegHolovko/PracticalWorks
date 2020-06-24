package com.wdn.practicalworks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ConnectionInit2 {

    @Bean
    public Connection withDriverManager() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "TESTUSER";
        String pwd = "12345678";
        return DriverManager.getConnection(url, user,pwd);
    }
}
