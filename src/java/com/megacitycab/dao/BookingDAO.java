package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Data Access Object (DAO) for handling booking-related operations in the database.
 * <p>
 * This class encapsulates all CRUD operations for Booking objects. It follows the DAO pattern,
 * separating data persistence logic from business logic. It is implemented as a Singleton
 * to ensure a single point of access for booking database operations.
 * </p>
 */
public class BookingDAO {
    // Singleton instance
    private static BookingDAO instance;

    // Private constructor to enforce singleton pattern
    private BookingDAO() {}

    /**
     * Returns the singleton instance of BookingDAO.
     *
     * @return the singleton instance.
     */
    public static synchronized BookingDAO getInstance() {
        if (instance == null) {
            instance = new BookingDAO();
        }
        return instance;
    }

    /**
     * Adds a new booking record to the database.
     *
     * @param booking the Booking object containing booking details.
     * @return true if the booking is added successfully; false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean addBooking(Booking booking) throws SQLException {
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
            stmt.setDate(6, new java.sql.Date(bookingDate != null ? bookingDate.getTime() : System.currentTimeMillis()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        }
    }

    /**
     * Retrieves a booking from the database by its booking number.
     *
     * @param bookingNumber the unique identifier of the booking.
     * @return the Booking object if found; otherwise, null.
     * @throws SQLException if a database access error occurs.
     */
    public Booking getBooking(String bookingNumber) throws SQLException {
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
                    java.sql.Date sqlDate = rs.getDate("bookingDate");
                    Date bookingDate = sqlDate != null ? new Date(sqlDate.getTime()) : null;


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
        }
        return booking;
    }

    /**
     * Retrieves all bookings from the database.
     *
     * @return a list of Booking objects.
     * @throws SQLException if a database access error occurs.
     */
    public List<Booking> getAllBookings() throws SQLException {
        String sql = "SELECT bookingNumber, customerName, customerAddress, telephoneNumber, destination, bookingDate FROM bookings";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String bNumber = rs.getString("bookingNumber");
                String customerName = rs.getString("customerName");
                String customerAddress = rs.getString("customerAddress");
                String telephoneNumber = rs.getString("telephoneNumber");
                String destination = rs.getString("destination");
                Timestamp ts = rs.getTimestamp("bookingDate");
                Date bookingDate = ts != null ? new Date(ts.getTime()) : null;

                Booking booking = new Booking.Builder(bNumber)
                        .customerName(customerName)
                        .customerAddress(customerAddress)
                        .telephoneNumber(telephoneNumber)
                        .destination(destination)
                        .bookingDate(bookingDate)
                        .build();

                bookings.add(booking);
            }
        }
        return bookings;
    }
}
