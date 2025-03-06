package com.megacitycab.dao;

import com.megacitycab.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
<<<<<<< HEAD
import java.sql.Timestamp;
import java.util.Date;

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
=======
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DAO for handling booking-related operations in the database.
 * Assumes the table "bookings" has the following columns:
 * bookingNumber, customerName, pickupLocation, telephoneNumber, destination, bookingDate, customerRegNo,
 * vehicleType, vehicleRegId, Vbrand, Vmodel, Vseating
 */
public class BookingDAO {
    private static BookingDAO instance;

    private BookingDAO() {}

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    public static synchronized BookingDAO getInstance() {
        if (instance == null) {
            instance = new BookingDAO();
        }
        return instance;
    }

    /**
<<<<<<< HEAD
     * Adds a new booking record to the database.
     *
     * @param booking the Booking object containing booking details.
     * @return true if the booking is added successfully; false otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (bookingNumber, customerName, customerAddress, telephoneNumber, destination, bookingDate) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
=======
     * Inserts a booking into the database.
     *
     * @param booking The booking to add.
     * @return true if insertion was successful.
     * @throws SQLException If an SQL error occurs.
     */
    public boolean addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO bookings (bookingNumber, customerName, pickupLocation, telephoneNumber, destination, bookingDate, customerRegNo, vehicleType, vehicleRegId, Vbrand, Vmodel, Vseating) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, booking.getBookingNumber());
            stmt.setString(2, booking.getCustomerName());
            stmt.setString(3, booking.getCustomerAddress());
            stmt.setString(4, booking.getTelephoneNumber());
            stmt.setString(5, booking.getDestination());
            Date bookingDate = booking.getBookingDate();
<<<<<<< HEAD
            stmt.setTimestamp(6, new Timestamp(bookingDate != null ? bookingDate.getTime() : System.currentTimeMillis()));

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
=======
            stmt.setDate(6, new java.sql.Date(bookingDate != null ? bookingDate.getTime() : System.currentTimeMillis()));
            stmt.setString(7, booking.getCustomerRegNo());
            stmt.setString(8, booking.getVehicleType());
            stmt.setString(9, booking.getVehicleRegId());
            stmt.setString(10, booking.getVbrand());
            stmt.setString(11, booking.getVmodel());
            stmt.setString(12, booking.getVseating());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
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
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        }
    }

    /**
<<<<<<< HEAD
     * Retrieves a booking from the database by its booking number.
     *
     * @param bookingNumber the unique identifier of the booking.
     * @return the Booking object if found; otherwise, null.
     * @throws SQLException if a database access error occurs.
     */
    public Booking getBooking(String bookingNumber) throws SQLException {
        String sql = "SELECT bookingNumber, customerName, customerAddress, telephoneNumber, destination, bookingDate "
=======
     * Deletes a booking from the database using its booking number.
     *
     * @param bookingNumber The unique booking number.
     * @return true if deletion was successful.
     * @throws SQLException If an SQL error occurs.
     */
    public boolean deleteBooking(String bookingNumber) throws SQLException {
        String sql = "DELETE FROM bookings WHERE bookingNumber = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookingNumber);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Deleted booking: " + bookingNumber);
            } else {
                System.err.println("No booking found with bookingNumber: " + bookingNumber);
            }
            return rowsDeleted > 0;
        } catch (SQLException ex) {
            System.err.println("SQLException in deleteBooking:");
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("Error Code: " + ex.getErrorCode());
            System.err.println("Message: " + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * Retrieves a booking from the database using its booking number.
     *
     * @param bookingNumber The unique booking number.
     * @return The Booking object if found, otherwise null.
     * @throws SQLException If an SQL error occurs.
     */
    public Booking getBooking(String bookingNumber) throws SQLException {
        String sql = "SELECT bookingNumber, customerName, pickupLocation, telephoneNumber, destination, customerRegNo, bookingDate, vehicleType, vehicleRegId, Vbrand, Vmodel, Vseating "
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
                   + "FROM bookings WHERE bookingNumber = ?";
        Booking booking = null;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, bookingNumber);
<<<<<<< HEAD

=======
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String bNumber = rs.getString("bookingNumber");
                    String customerName = rs.getString("customerName");
<<<<<<< HEAD
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
=======
                    String customerAddress = rs.getString("pickupLocation");
                    String telephoneNumber = rs.getString("telephoneNumber");
                    String destination = rs.getString("destination");
                    java.sql.Date sqlDate = rs.getDate("bookingDate");
                    Date bookingDate = sqlDate != null ? new Date(sqlDate.getTime()) : null;
                    String customerRegNo = rs.getString("customerRegNo");
                    String vehicleType = rs.getString("vehicleType");
                    String vehicleRegId = rs.getString("vehicleRegId");
                    String vbrand = rs.getString("Vbrand");
                    String vmodel = rs.getString("Vmodel");
                    String vseating = rs.getString("Vseating");

                    booking = new Booking.Builder(bNumber)
                            .customerName(customerName)
                            .customerAddress(customerAddress)
                            .telephoneNumber(telephoneNumber)
                            .destination(destination)
                            .bookingDate(bookingDate)
                            .customerRegNo(customerRegNo)
                            .vehicleType(vehicleType)
                            .vehicleRegId(vehicleRegId)
                            .vbrand(vbrand)
                            .vmodel(vmodel)
                            .vseating(vseating)
                            .build();
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
                }
            }
        }
        return booking;
    }
<<<<<<< HEAD
=======

    /**
     * Retrieves all bookings from the database.
     *
     * @return A list of Booking objects.
     * @throws SQLException If an SQL error occurs.
     */
    public List<Booking> getAllBookings() throws SQLException {
        String sql = "SELECT bookingNumber, customerName, pickupLocation, telephoneNumber, destination, customerRegNo, bookingDate, vehicleType, vehicleRegId, Vbrand, Vmodel, Vseating FROM bookings";
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
                java.sql.Timestamp ts = rs.getTimestamp("bookingDate");
                Date bookingDate = ts != null ? new Date(ts.getTime()) : null;
                String customerRegNo = rs.getString("customerRegNo");
                String vehicleType = rs.getString("vehicleType");
                String vehicleRegId = rs.getString("vehicleRegId");
                String vbrand = rs.getString("Vbrand");
                String vmodel = rs.getString("Vmodel");
                String vseating = rs.getString("Vseating");

                Booking booking = new Booking.Builder(bNumber)
                        .customerName(customerName)
                        .customerAddress(customerAddress)
                        .telephoneNumber(telephoneNumber)
                        .destination(destination)
                        .bookingDate(bookingDate)
                        .customerRegNo(customerRegNo)
                        .vehicleType(vehicleType)
                        .vehicleRegId(vehicleRegId)
                        .vbrand(vbrand)
                        .vmodel(vmodel)
                        .vseating(vseating)
                        .build();
                bookings.add(booking);
            }
        }
        return bookings;
    }
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
}
