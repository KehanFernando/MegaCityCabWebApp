package com.megacitycab.controller;

import com.megacitycab.model.Customer;
import com.megacitycab.service.CustomerService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet controller for handling customer registration.
 * Receives form submissions from customerReg.jsp, creates a Customer object,
 * and delegates persistence to the service layer.
 */
@WebServlet("/CustomerRegistrationServlet")
public class CustomerRegistrationServlet extends HttpServlet {

    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        String registrationNumber = request.getParameter("registrationNumber");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String nic = request.getParameter("nic");
        String telephone = request.getParameter("telephone");

        // Build the Customer object using the Builder pattern
        Customer customer = new Customer.Builder(registrationNumber)
                .name(name)
                .address(address)
                .nic(nic)
                .telephone(telephone)
                .build();

        try {
            // Add the customer using the service layer
            boolean success = customerService.addCustomer(customer);

            if (success) {
                // Set success message and forward back to customerReg.jsp
                request.setAttribute("message", "Customer registered successfully with Registration Number: " + registrationNumber);
            } else {
                request.setAttribute("errorMessage", "Failed to register customer. Please try again.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
        }
        // Forward to the same registration page so that messages are displayed
        request.getRequestDispatcher("customerReg.jsp").forward(request, response);
    }
}
