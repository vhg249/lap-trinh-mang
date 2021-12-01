package com.Server;

import java.sql.*;

public class ConnectDatabase {
    public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
        String hostName = "localhost",
                dbName = "monopoly",
                userName = "root",
                password = "";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionURL = "jdbc:mysql://"+hostName+":3306/"+dbName+"?zeroDateTimeBehaviour=convertToNull";
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
