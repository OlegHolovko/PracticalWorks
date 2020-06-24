package com.wdn.practicalworks.dao;

public interface DAOConnection {
    void connect();
    void disconnect();
    void selectAll();
}
