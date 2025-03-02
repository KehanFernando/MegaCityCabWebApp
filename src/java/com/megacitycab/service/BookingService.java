package com.megacitycab.service;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;
import java.sql.SQLException;
import java.util.List;

/**
 * Provides booking services for the Mega City Cab system.
 */
public class BookingService {
    private static BookingService instance;

    private BookingService() {}

    public static synchronized BookingService getInstance() {
        if (instance == null) {
            instance = new BookingService();
        }
        return instance;
    }

    public boolean addBooking(Booking booking) {
        try {
            boolean added = BookingDAO.getInstance().addBooking(booking);
            if (!added) {
                System.err.println("BookingService: addBooking returned false for booking: " + booking);
            }
            return added;
        } catch (SQLException ex) {
            System.err.println("BookingService: SQLException when adding booking: " + booking);
            ex.printStackTrace();
            return false;
        }
    }

    public Booking getBooking(String bookingNumber) {
        try {
            return BookingDAO.getInstance().getBooking(bookingNumber);
        } catch (SQLException ex) {
            System.err.println("BookingService: SQLException when retrieving booking with bookingNumber: " + bookingNumber);
            ex.printStackTrace();
            return null;
        }
    }

    public List<Booking> getAllBookings() {
        try {
            return BookingDAO.getInstance().getAllBookings();
        } catch (SQLException ex) {
            System.err.println("BookingService: SQLException when retrieving all bookings.");
            ex.printStackTrace();
            return null;
        }
    }
    
    /**
     * Deletes a booking with the given booking number.
     * @param bookingNumber the booking number to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteBooking(String bookingNumber) {
        try {
            return BookingDAO.getInstance().deleteBooking(bookingNumber);
        } catch (SQLException ex) {
            System.err.println("BookingService: SQLException when deleting booking with bookingNumber: " + bookingNumber);
            ex.printStackTrace();
            return false;
        }
    }
}
