package com.megacitycab.service;

import com.megacitycab.dao.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

<<<<<<< HEAD
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
=======
public class AuthenticationService {
    private static AuthenticationService instance;

    private AuthenticationService() {}

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    public static synchronized AuthenticationService getInstance() {
        if (instance == null) {
            instance = new AuthenticationService();
        }
        return instance;
    }

<<<<<<< HEAD
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

=======
    public boolean authenticate(String username, String password) {
        // Trim input to remove accidental spaces
        username = username != null ? username.trim() : "";
        password = password != null ? password.trim() : "";

        boolean isAuthenticated = false;
        String sql = "SELECT password FROM users WHERE username = ?";

        System.out.println("AuthenticationService: Trying to authenticate user: '" + username + "'");

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
<<<<<<< HEAD
=======
            System.out.println("AuthenticationService: Executing query: " + stmt);
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
<<<<<<< HEAD
                    // For production, consider hashing and secure password comparison.
                    isAuthenticated = storedPassword.equals(password);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error during authentication: " + ex.getMessage());
=======
                    System.out.println("AuthenticationService: Retrieved stored password: '" + storedPassword + "'");
                    System.out.println("AuthenticationService: Provided password: '" + password + "'");
                    
                    // Compare the two passwords exactly.
                    if (storedPassword.equals(password)) {
                        isAuthenticated = true;
                        System.out.println("AuthenticationService: Authentication successful for user: '" + username + "'");
                    } else {
                        System.out.println("AuthenticationService: Password mismatch for user: '" + username + "'");
                    }
                } else {
                    System.out.println("AuthenticationService: No record found for user: '" + username + "'");
                }
            }
        } catch (SQLException ex) {
            System.err.println("AuthenticationService: Error during authentication: " + ex.getMessage());
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
            ex.printStackTrace();
        }
        return isAuthenticated;
    }
}
