package com.megacitycab.service;

import com.megacitycab.dao.DBConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Provides billing services for the Mega City Cab system.
 * <p>
 * This service is implemented as a Singleton to ensure a single point of billing calculation.
 * It retrieves booking details from a MySQL database and calculates the total amount using a
 * predefined base fare, tax rate, and any applicable discounts. The service adheres to SOLID and
 * architectural principles by separating billing logic from data access (handled by DBConnectionManager).
 * </p>
 */

public class BillingService {
    // Singleton instance
    private static BillingService instance;

    // Private constructor prevents external instantiation.
    private BillingService() {
    }

    /**
     * Returns the singleton instance of BillingService.
     *
     * @return the singleton instance.
     */
    public static synchronized BillingService getInstance() {
        if (instance == null) {
            instance = new BillingService();
        }
        return instance;
    }

    /**
     * Calculates the bill for a given booking number.
     * <p>
     * This method connects to the database to verify the existence of the booking and then applies
     * a billing calculation based on a dummy base fare, tax rate, and discount.
     * </p>
     *
     * @param bookingNumber the unique booking identifier.
     * @return the total bill amount; returns 0.0 if the booking is not found.
     */
    public double calculateBill(String bookingNumber) {
        // Dummy pricing parameters (in a real application these could come from configuration or business logic)
        final double baseFare = 100.0;
        final double taxRate = 0.10;  // 10% tax
        final double discount = 0.0;  // For example, discount could be calculated based on promotions
        
        double totalAmount = 0.0;
        String sql = "SELECT bookingNumber FROM bookings WHERE bookingNumber = ?";

        // Using try-with-resources to ensure proper resource management
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // If booking exists, calculate the total bill.
                    totalAmount = baseFare + (baseFare * taxRate) - discount;
                } else {
                    System.err.println("Booking not found for booking number: " + bookingNumber);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while calculating bill: " + ex.getMessage());
            ex.printStackTrace();
        }
        return totalAmount;
    }
}
