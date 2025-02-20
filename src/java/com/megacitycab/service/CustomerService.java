package com.megacitycab.service;

import com.megacitycab.dao.CustomerDAO;
import com.megacitycab.model.Customer;
import java.sql.SQLException;

/**
 * Service class for handling business logic related to Customer.
 * This class mediates between the controller (servlet) and the DAO.
 */
public class CustomerService {

    private CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = CustomerDAO.getInstance();
    }

    /**
     * Adds a new customer to the system.
     *
     * @param customer the Customer object containing registration details.
     * @return true if added successfully; false otherwise.
     * @throws SQLException if a database error occurs.
     */
    public boolean addCustomer(Customer customer) throws SQLException {
        return customerDAO.addCustomer(customer);
    }

    /**
     * Retrieves a customer by registration number.
     *
     * @param registrationNumber the unique registration number.
     * @return the Customer object if found; otherwise, null.
     * @throws SQLException if a database error occurs.
     */
    public Customer getCustomer(String registrationNumber) throws SQLException {
        return customerDAO.getCustomer(registrationNumber);
    }
}
