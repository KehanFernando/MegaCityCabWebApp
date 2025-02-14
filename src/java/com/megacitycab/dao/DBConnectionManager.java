package com.megacitycab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnectionManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/megacitycab?useSSL=false&serverTimezone=UTC";
    private static final String DB_USER = "root"; // Use your actual MySQL username
    private static final String DB_PASSWORD = "admin";  // Use your actual MySQL password (if any)

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error loading MySQL JDBC Driver: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private DBConnectionManager() { }

    public static Connection getConnection() throws SQLException {
    System.out.println("DBConnectionManager: Connecting to " + DB_URL + " as " + DB_USER);
    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
}

}
