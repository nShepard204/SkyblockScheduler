package com.example.skyscheduler;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLManager {
    @Value("${neon.connString}")
    public String connStr;

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(this.connStr);
        return conn;
    }
}
