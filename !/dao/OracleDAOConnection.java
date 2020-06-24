package com.wdn.practicalworks.dao;

import oracle.jdbc.driver.OracleDriver;

import java.sql.*;

public class OracleDAOConnection implements DAOConnection {

    private static OracleDAOConnection oracleDAOConnection;

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Driver driver;


    private OracleDAOConnection () {
        super();
    }

    public static OracleDAOConnection getInstance() {
        if (oracleDAOConnection != null) {
            return oracleDAOConnection;
        } else {
            oracleDAOConnection = new OracleDAOConnection();
            return oracleDAOConnection;
        }
    }


    @Override
    public void connect() {
        driver = new OracleDriver();
        try {
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "testuser", "12345678");
            if (!connection.isClosed()) {
                System.out.println("Connected successful!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void disconnect() {
        try {
            connection.close();
            System.out.println("Connection was closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectAll() {

    }
/*
    private Student parseStudent(ResultSet resultSet) {
        Student student = null;
        try {
            int id = resultSet.getInt("STUDENT_ID");
            String name = resultSet.getString("STUDENT_NAME");
            float scholarship = resultSet.getFloat("STUDENT_SCHOLARSHIP");
            student = new Student(id, name, scholarship);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
*/
}
