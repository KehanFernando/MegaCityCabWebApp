package BookingTest;

import com.megacitycab.dao.BookingDAO;
import com.megacitycab.model.Booking;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingTest {
    public static void main(String[] args) {
        BookingDAO bookingDAO = BookingDAO.getInstance(); // Get singleton instance

        // Test Case 1: Successful Booking Insertion
        System.out.println("\nTest Case 1: Successful Booking Insertion");
        if (addBookingTest("BKN10001", "John Doe", "123 Main St", "0712345678", "City Center",
                "2025-03-15", "CUS_1001", "Car", "VH-12345", "Toyota", "Corolla", "5", bookingDAO)) {
            System.out.println("✅ Booking Insertion Successful - Passed");
        } else {
            System.out.println("❌ Booking Insertion Failed - Failed");
        }

        // Test Case 2: Retrieve Booking By Booking Number
        System.out.println("\nTest Case 2: Retrieve Booking By Booking Number");
        if (retrieveBookingTest("BKN10001", bookingDAO)) {
            System.out.println("✅ Booking Retrieval Successful - Passed");
        } else {
            System.out.println("❌ Booking Retrieval Failed - Failed");
        }

        // Test Case 3: Attempt to Add Duplicate Booking Number (Should Fail)
        System.out.println("\nTest Case 3: Attempt to Add Duplicate Booking Number");
        if (!addBookingTest("BKN10001", "Jane Doe", "456 Elm St", "0776543210", "Airport",
                "2025-03-20", "CUS_1002", "SUV", "VH-56789", "Honda", "CRV", "7", bookingDAO)) {
            System.out.println("✅ Duplicate Booking Number Rejected - Passed");
        } else {
            System.out.println("❌ Duplicate Booking Number Allowed - Failed");
        }

        // Test Case 4: Retrieve All Bookings
        System.out.println("\nTest Case 4: Retrieve All Bookings");
        if (retrieveAllBookingsTest(bookingDAO)) {
            System.out.println("✅ Retrieved All Bookings Successfully - Passed");
        } else {
            System.out.println("❌ Failed to Retrieve All Bookings - Failed");
        }

        // Test Case 5: Delete Booking
        System.out.println("\nTest Case 5: Delete Booking");
        if (deleteBookingTest("BKN10001", bookingDAO)) {
            System.out.println("✅ Booking Deletion Successful - Passed");
        } else {
            System.out.println("❌ Booking Deletion Failed - Failed");
        }
    }

    // Function to Test Booking Insertion using DAO
    public static boolean addBookingTest(String bookingNumber, String customerName, String customerAddress,
                                         String telephoneNumber, String destination, String bookingDateStr,
                                         String customerRegNo, String vehicleType, String vehicleRegId,
                                         String brand, String model, String seating, BookingDAO bookingDAO) {
        Date bookingDate = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            bookingDate = sdf.parse(bookingDateStr);
        } catch (Exception ex) {
            bookingDate = new Date();
        }

        Booking booking = new Booking.Builder(bookingNumber)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .telephoneNumber(telephoneNumber)
                .destination(destination)
                .bookingDate(bookingDate)
                .customerRegNo(customerRegNo)
                .vehicleType(vehicleType)
                .vehicleRegId(vehicleRegId)
                .vbrand(brand)
                .vmodel(model)
                .vseating(seating)
                .build();

        try {
            return bookingDAO.addBooking(booking);
        } catch (Exception e) {
            System.out.println("❌ Error during booking insertion: " + e.getMessage());
            return false;
        }
    }

    // Function to Test Booking Retrieval by Booking Number
    public static boolean retrieveBookingTest(String bookingNumber, BookingDAO bookingDAO) {
        try {
            Booking booking = bookingDAO.getBooking(bookingNumber);
            if (booking != null) {
                System.out.println("ℹ️ Retrieved Booking Details: " + booking);
                return true;
            } else {
                System.out.println("ℹ️ Booking not found with number: " + bookingNumber);
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Error during booking retrieval: " + e.getMessage());
            return false;
        }
    }

    // Function to Test Retrieving All Bookings
    public static boolean retrieveAllBookingsTest(BookingDAO bookingDAO) {
        try {
            List<Booking> bookings = bookingDAO.getAllBookings();
            if (bookings != null && !bookings.isEmpty()) {
                System.out.println("ℹ️ Retrieved " + bookings.size() + " Booking(s):");
                for (Booking booking : bookings) {
                    System.out.println(booking);
                }
                return true;
            } else {
                System.out.println("ℹ️ No bookings found.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("❌ Error retrieving all bookings: " + e.getMessage());
            return false;
        }
    }

    // Function to Test Booking Deletion
    public static boolean deleteBookingTest(String bookingNumber, BookingDAO bookingDAO) {
        try {
            return bookingDAO.deleteBooking(bookingNumber);
        } catch (Exception e) {
            System.out.println("❌ Error during booking deletion: " + e.getMessage());
            return false;
        }
    }
}
