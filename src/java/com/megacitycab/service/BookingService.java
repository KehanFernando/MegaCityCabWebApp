package com.megacitycab.service;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

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
        try {
            return BookingDAO.getInstance().addBooking(booking);
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
        try {
            return BookingDAO.getInstance().getBooking(bookingNumber);
        } catch (SQLException ex) {
            System.err.println("Error while retrieving booking: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Retrieves all bookings from the database.
     *
     * @return a list of Booking objects.
     */
    public List<Booking> getAllBookings() {
        try {
            return BookingDAO.getInstance().getAllBookings();
        } catch (SQLException ex) {
            System.err.println("Error while retrieving all bookings: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
