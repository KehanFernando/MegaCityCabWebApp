package com.megacitycab.service;

import com.megacitycab.dao.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Provides authentication services for the Mega City Cab system using MySQL DB.
 * <p>
 * This class is implemented as a singleton to ensure a single point of authentication management.
 * It uses JDBC to retrieve user credentials from the database and validate them.
 * </p>
 */

public class AuthenticationService {
    // Singleton instance of AuthenticationService.
    private static AuthenticationService instance;

    // Private constructor prevents external instantiation.
    private AuthenticationService() {
        // Initialize any necessary resources here.
    }

    /**
     * Returns the singleton instance of AuthenticationService.
     *
     * @return the singleton instance.
     */
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

    /**
     * Authenticates a user by comparing the provided credentials against the database record.
     * <p>
     * The users table is assumed to contain at least the following columns:
     * <ul>
     *   <li>username (VARCHAR)</li>
     *   <li>password (VARCHAR)</li>
     *   <li>other details as required</li>
     * </ul>
     * </p>
     *
     * @param username the username to authenticate.
     * @param password the password provided by the user.
     * @return true if the credentials are valid; false otherwise.
     */
    public boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;
        String sql = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    // For production, consider hashing and secure password comparison.
                    isAuthenticated = storedPassword.equals(password);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error during authentication: " + ex.getMessage());
            ex.printStackTrace();
        }
        return isAuthenticated;
    }
}
