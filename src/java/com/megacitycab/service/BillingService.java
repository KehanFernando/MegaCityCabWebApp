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
 * It retrieves booking details (including customer address and destination) from a MySQL database,
 * calculates the distance (using a dummy method that could be replaced by a Google Maps integration),
 * and then computes the total bill using a base fare, per-mile rate, and tax.
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
     * DTO class to encapsulate billing details.
     */
    public static class BillingInfo {
        private String bookingNumber;
        private String customerAddress;
        private String destination;
        private double distance; // in miles
        private double totalAmount;

        public BillingInfo(String bookingNumber, String customerAddress, String destination, double distance, double totalAmount) {
            this.bookingNumber = bookingNumber;
            this.customerAddress = customerAddress;
            this.destination = destination;
            this.distance = distance;
            this.totalAmount = totalAmount;
        }

        public String getBookingNumber() {
            return bookingNumber;
        }

        public String getCustomerAddress() {
            return customerAddress;
        }

        public String getDestination() {
            return destination;
        }

        public double getDistance() {
            return distance;
        }

        public double getTotalAmount() {
            return totalAmount;
        }
    }

    /**
     * Calculates the billing information for a given booking number.
     * <p>
     * The method retrieves the booking record (customer address and destination) from the database,
     * calculates the distance between the two locations using a dummy implementation (which could be
     * replaced by a call to the Google Maps API), and then computes the total bill.
     * </p>
     *
     * @param bookingNumber the unique booking identifier.
     * @return an instance of BillingInfo containing detailed billing information, or null if not found.
     */
    public BillingInfo calculateBill(String bookingNumber) {
        String sql = "SELECT bookingNumber, customerAddress, destination FROM bookings WHERE bookingNumber = ?";
        BillingInfo billingInfo = null;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String customerAddress = rs.getString("customerAddress");
                    String destination = rs.getString("destination");

                    // Retrieve the distance (in miles) using a dummy integration method.
                    double distance = getDistance(customerAddress, destination);

                    // Billing parameters.
                    final double baseFare = 50.0;
                    final double perMileRate = 2.0;
                    final double taxRate = 0.10; // 10% tax

                    double amountBeforeTax = baseFare + (distance * perMileRate);
                    double totalAmount = amountBeforeTax + (amountBeforeTax * taxRate);

                    billingInfo = new BillingInfo(bookingNumber, customerAddress, destination, distance, totalAmount);
                } else {
                    System.err.println("Booking not found for booking number: " + bookingNumber);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while calculating bill: " + ex.getMessage());
            ex.printStackTrace();
        }
        return billingInfo;
    }

    /**
     * Dummy implementation for calculating distance between two addresses.
     * <p>
     * In a real-world scenario, this method would integrate with the Google Maps API (or similar)
     * to retrieve the actual mileage/distance between the origin and destination.
     * </p>
     *
     * @param origin      the starting address.
     * @param destination the destination address.
     * @return the distance in miles.
     */
    private double getDistance(String origin, String destination) {
        // TODO: Replace with real Google Maps API integration to compute distance.
        // For now, we simulate by returning a fixed value.
        return 10.0;
    }
}
