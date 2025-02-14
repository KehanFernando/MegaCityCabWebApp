package com.megacitycab.service;

import com.megacitycab.dao.DBConnectionManager;
import com.megacitycab.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Provides booking services for the Mega City Cab system.
 * <p>
 * This class handles the business logic for creating and retrieving bookings.
 * It is implemented as a singleton to ensure a centralized access point and
 * uses JDBC via DBConnectionManager to interact with the MySQL database.
 * </p>
 */

public class BookingService {
    // Singleton instance
    private static BookingService instance;

    // Private constructor to prevent instantiation
    private BookingService() {
    }

    /**
     * Returns the singleton instance of BookingService.
     *
     * @return the singleton instance.
     */
    public static synchronized BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    /**
     * Adds a new booking to the database.
     *
     * @param booking the Booking object containing the booking details.
     * @return true if the booking was added successfully; false otherwise.
     */
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO bookings (bookingNumber, customerName, customerAddress, telephoneNumber, destination, bookingDate) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingNumber());
            stmt.setString(2, booking.getCustomerName());
            stmt.setString(3, booking.getCustomerAddress());
            stmt.setString(4, booking.getTelephoneNumber());
            stmt.setString(5, booking.getDestination());
            Date bookingDate = booking.getBookingDate();
            // Use current timestamp if bookingDate is null
            stmt.setTimestamp(6, new Timestamp(bookingDate != null ? bookingDate.getTime() : System.currentTimeMillis()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.err.println("Error while adding booking: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a booking from the database based on the booking number.
     *
     * @param bookingNumber the unique identifier of the booking.
     * @return the Booking object if found; otherwise, null.
     */
    public Booking getBooking(String bookingNumber) {
        String sql = "SELECT bookingNumber, customerName, customerAddress, telephoneNumber, destination, bookingDate "
                   + "FROM bookings WHERE bookingNumber = ?";
        Booking booking = null;
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String bNumber = rs.getString("bookingNumber");
                    String customerName = rs.getString("customerName");
                    String customerAddress = rs.getString("customerAddress");
                    String telephoneNumber = rs.getString("telephoneNumber");
                    String destination = rs.getString("destination");
                    Timestamp ts = rs.getTimestamp("bookingDate");
                    Date bookingDate = ts != null ? new Date(ts.getTime()) : null;

                    // Build the Booking object using the Builder pattern
                    booking = new Booking.Builder(bNumber)
                            .customerName(customerName)
                            .customerAddress(customerAddress)
                            .telephoneNumber(telephoneNumber)
                            .destination(destination)
                            .bookingDate(bookingDate)
                            .build();
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while retrieving booking: " + ex.getMessage());
            ex.printStackTrace();
        }
        return booking;
    }
}
