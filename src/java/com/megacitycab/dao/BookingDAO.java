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
 * DAO for handling booking-related operations in the database.
 * Ensure that your DB table 'bookings' has a column named 'customerRegNo'.
 */
public class BookingDAO {
    private static BookingDAO instance;

    private BookingDAO() {}

    public static synchronized BookingDAO getInstance() {
        if (instance == null) {
            instance = new BookingDAO();
        }
        return instance;
    }

    public boolean addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (bookingNumber, customerName, pickupLocation, telephoneNumber, destination, bookingDate, customerRegNo) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingNumber());
            stmt.setString(2, booking.getCustomerName());
            stmt.setString(3, booking.getCustomerAddress());
            stmt.setString(4, booking.getTelephoneNumber());
            stmt.setString(5, booking.getDestination());
            Date bookingDate = booking.getBookingDate();
            stmt.setDate(6, new java.sql.Date(bookingDate != null ? bookingDate.getTime() : System.currentTimeMillis()));
            stmt.setString(7, booking.getCustomerRegNo());

            int rowsInserted = stmt.executeUpdate();
            if(rowsInserted > 0) {
                System.out.println("Inserted booking: " + booking);
            } else {
                System.err.println("No rows inserted for booking: " + booking);
            }
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.err.println("SQLException in addBooking:");
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("Message: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public Booking getBooking(String bookingNumber) throws SQLException {
        String sql = "SELECT bookingNumber, customerName, pickupLocation, telephoneNumber, destination, customerRegNo, bookingDate "
                   + "FROM bookings WHERE bookingNumber = ?";
        Booking booking = null;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String bNumber = rs.getString("bookingNumber");
                    String customerName = rs.getString("customerName");
                    String customerAddress = rs.getString("pickupLocation");
                    String telephoneNumber = rs.getString("telephoneNumber");
                    String destination = rs.getString("destination");
                    java.sql.Date sqlDate = rs.getDate("bookingDate");
                    Date bookingDate = sqlDate != null ? new Date(sqlDate.getTime()) : null;
                    String customerRegNo = rs.getString("customerRegNo");

                    booking = new Booking.Builder(bNumber)
                                .customerName(customerName)
                                .customerAddress(customerAddress)
                                .telephoneNumber(telephoneNumber)
                                .destination(destination)
                                .bookingDate(bookingDate)
                                .customerRegNo(customerRegNo)
                                .build();
                }
            }
        }
        return booking;
    }

    public List<Booking> getAllBookings() throws SQLException {
        String sql = "SELECT bookingNumber, customerName, pickupLocation, telephoneNumber, destination, customerRegNo, bookingDate FROM bookings";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String bNumber = rs.getString("bookingNumber");
                String customerName = rs.getString("customerName");
                String customerAddress = rs.getString("pickupLocation");
                String telephoneNumber = rs.getString("telephoneNumber");
                String destination = rs.getString("destination");
                Timestamp ts = rs.getTimestamp("bookingDate");
                Date bookingDate = ts != null ? new Date(ts.getTime()) : null;
                String customerRegNo = rs.getString("customerRegNo");

                Booking booking = new Booking.Builder(bNumber)
                        .customerName(customerName)
                        .customerAddress(customerAddress)
                        .telephoneNumber(telephoneNumber)
                        .destination(destination)
                        .bookingDate(bookingDate)
                        .customerRegNo(customerRegNo)
                        .build();
                bookings.add(booking);
            }
        }
        return bookings;
    }
}
