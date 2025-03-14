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
<<<<<<< HEAD
 * It retrieves booking details from a MySQL database and calculates the total amount using a
 * predefined base fare, tax rate, and any applicable discounts. The service adheres to SOLID and
 * architectural principles by separating billing logic from data access (handled by DBConnectionManager).
 * </p>
 */

public class BillingService {
=======
 * It retrieves booking details (including customer address and destination) from a MySQL database.
 * It can either return basic booking info (distance=0) or compute a full bill if a manual
 * distance is provided.
 * </p>
 */
public class BillingService {

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
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
<<<<<<< HEAD
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
=======
     * DTO class to encapsulate billing details.
     */
    public static class BillingInfo {
        private String bookingNumber;
        private String pickupLocation;
        private String destination;
        private double distance; // in miles
        private double baseFare;
        private double distanceCost;
        private double taxAmount;
        private double totalAmount;

        public BillingInfo(String bookingNumber, String pickupLocation, String destination) {
            this.bookingNumber = bookingNumber;
            this.pickupLocation = pickupLocation;
            this.destination = destination;
            this.distance = 0;
            this.baseFare = 0;
            this.distanceCost = 0;
            this.taxAmount = 0;
            this.totalAmount = 0;
        }

        // Getters and setters
        public String getBookingNumber() {
            return bookingNumber;
        }

        public String getpickupLocation() {
            return pickupLocation;
        }

        public String getDestination() {
            return destination;
        }

        public double getDistance() {
            return distance;
        }

        public void setDistance(double distance) {
            this.distance = distance;
        }

        public double getBaseFare() {
            return baseFare;
        }

        public void setBaseFare(double baseFare) {
            this.baseFare = baseFare;
        }

        public double getDistanceCost() {
            return distanceCost;
        }

        public void setDistanceCost(double distanceCost) {
            this.distanceCost = distanceCost;
        }

        public double getTaxAmount() {
            return taxAmount;
        }

        public void setTaxAmount(double taxAmount) {
            this.taxAmount = taxAmount;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }
    }

    /**
     * Returns a BillingInfo object with only bookingNumber, pickupLocation, destination
     * (distance, cost, etc. remain zero). This is for preview before distance is known.
     *
     * @param bookingNumber the booking number to look up
     * @return a BillingInfo object, or null if not found
     */
    public BillingInfo getBookingDetails(String bookingNumber) {
        String sql = "SELECT bookingNumber, pickupLocation, destination FROM bookings WHERE bookingNumber = ?";
        BillingInfo billingInfo = null;

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
<<<<<<< HEAD
                    // If booking exists, calculate the total bill.
                    totalAmount = baseFare + (baseFare * taxRate) - discount;
=======
                    String dbBookingNumber = rs.getString("bookingNumber");
                    String pickupLocation = rs.getString("pickupLocation");
                    String destination = rs.getString("destination");

                    // Return basic booking details (distance = 0, cost = 0, etc.)
                    billingInfo = new BillingInfo(dbBookingNumber, pickupLocation, destination);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while retrieving booking details: " + ex.getMessage());
            ex.printStackTrace();
        }
        return billingInfo;
    }

    /**
     * Calculates the billing information using the manually provided distance.
     *
     * @param bookingNumber  the unique booking identifier.
     * @param manualDistance the manually provided distance in miles.
     * @return a fully populated BillingInfo object, or null if the booking is not found
     */
    public BillingInfo calculateBill(String bookingNumber, double manualDistance) {
        String sql = "SELECT bookingNumber, pickupLocation, destination FROM bookings WHERE bookingNumber = ?";
        BillingInfo billingInfo = null;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String dbBookingNumber = rs.getString("bookingNumber");
                    String pickupLocation = rs.getString("pickupLocation");
                    String destination = rs.getString("destination");

                    billingInfo = new BillingInfo(dbBookingNumber, pickupLocation, destination);
                    billingInfo.setDistance(manualDistance);

                    // Billing parameters
                    final double baseFare = 50.0;
                    final double perMileRate = 2.0;
                    final double taxRate = 0.10; // 10%

                    double distanceCost = manualDistance * perMileRate;
                    double amountBeforeTax = baseFare + distanceCost;
                    double taxAmount = amountBeforeTax * taxRate;
                    double totalAmount = amountBeforeTax + taxAmount;

                    // Populate fields
                    billingInfo.setBaseFare(baseFare);
                    billingInfo.setDistanceCost(distanceCost);
                    billingInfo.setTaxAmount(taxAmount);
                    billingInfo.setTotalAmount(totalAmount);
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
                } else {
                    System.err.println("Booking not found for booking number: " + bookingNumber);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while calculating bill: " + ex.getMessage());
            ex.printStackTrace();
        }
<<<<<<< HEAD
        return totalAmount;
=======
        return billingInfo;
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    }
}
