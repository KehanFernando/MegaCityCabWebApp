package com.megacitycab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connections for the Mega City Cab system.
 * <p>
 * This class uses the Singleton approach for managing the JDBC driver loading and provides
 * a central method to obtain new connections to the MySQL database.
 * </p>
 */


public class DBConnectionManager {
    // Update these with your actual database URL, username, and password.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/megacitycab"; 
    private static final String DB_USER = "dbuser";
    private static final String DB_PASSWORD = "dbpassword";

    // Static block to load the MySQL JDBC driver.
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Error loading MySQL JDBC Driver: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // Private constructor prevents instantiation.
    private DBConnectionManager() {}

    /**
     * Returns a new connection to the database.
     *
     * @return a Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
