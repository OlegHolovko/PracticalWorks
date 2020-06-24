package com.wdn.practicalworks.config;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection2 {
    private static Connection connection;
    private Driver driver;
    private static DbConnection2 instance;
    private DbConnection2() {
        driver = new OracleDriver();
        try {
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE",
                    "SYSTEM", "12345678");
            if (!connection.isClosed()) {
                System.out.println("Connected successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static DbConnection2 getInstance()
    {

        if(instance == null) {

            instance = new DbConnection2();

        }

        return instance;


    }
}
