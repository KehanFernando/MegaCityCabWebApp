package com.megacitycab.dao;


import com.megacitycab.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for handling customer-related operations in the database.
 * <p>
 * This class encapsulates all CRUD operations for Customer objects. It follows the DAO pattern,
 * separating data persistence logic from business logic, and is implemented as a Singleton to
 * ensure a single point of access for customer database operations.
 * </p>
 */

public class CustomerDAO {
    // Singleton instance of CustomerDAO
    private static CustomerDAO instance;

    // Private constructor to enforce Singleton pattern
    private CustomerDAO() {}

    /**
     * Returns the singleton instance of CustomerDAO.
     *
     * @return the singleton instance.
     */
    public static synchronized CustomerDAO getInstance() {
        if (instance == null) {
            instance = new CustomerDAO();
        }
        return instance;
    }

    /**
     * Adds a new customer record to the database.
     *
     * @param customer the Customer object containing customer details.
     * @return true if the customer is added successfully; false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (registrationNumber, name, address, nic, telephone) "
                   + "VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customer.getRegistrationNumber());
            stmt.setString(2, customer.getName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getNic());
            stmt.setString(5, customer.getTelephone());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    /**
     * Retrieves a customer from the database based on the registration number.
     *
     * @param registrationNumber the unique identifier for the customer.
     * @return the Customer object if found; otherwise, null.
     * @throws SQLException if a database access error occurs.
     */
    public Customer getCustomer(String registrationNumber) throws SQLException {
        String sql = "SELECT registrationNumber, name, address, nic, telephone FROM customers WHERE registrationNumber = ?";
        Customer customer = null;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, registrationNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String regNumber = rs.getString("registrationNumber");
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    String nic = rs.getString("nic");
                    String telephone = rs.getString("telephone");

                    // Build the Customer object using the Builder pattern
                    customer = new Customer.Builder(regNumber)
                            .name(name)
                            .address(address)
                            .nic(nic)
                            .telephone(telephone)
                            .build();
                }
            }
        }
        return customer;
    }
}
