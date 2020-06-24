package com.wdn.practicalworks.config;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {
    private static DataSource oracleDataSource;
    private static JdbcTemplate jdbcTemplate;
    static Connection conn = null;

    static
    {
        try {
            jdbcTemplate = new JdbcTemplate(oracleDataSource);
            conn = oracleDataSource.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return conn;
    }
}

