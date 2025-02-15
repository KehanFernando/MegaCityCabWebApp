package com.megacitycab.controller;

import com.megacitycab.model.Booking;
import com.megacitycab.service.BookingService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * BookingServlet handles booking-related HTTP requests.
 * <p>
 * GET requests are used for either retrieving all booking previews (when action=list)
 * or for retrieving booking details based on a booking number.
 * POST requests are used for adding a new booking to the system.
 * This servlet uses BookingService for business logic, following the MVC pattern.
 * </p>
 */
public class BookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    // Singleton instance of BookingService for booking operations.
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Obtain the singleton instance of BookingService.
        bookingService = BookingService.getInstance();
    }

    /**
     * Handles GET requests to retrieve booking details or a list of booking previews.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        // If action is "list", then retrieve all bookings for preview.
        if ("list".equalsIgnoreCase(action)) {
            List<Booking> bookings = bookingService.getAllBookings();
            if (bookings != null && !bookings.isEmpty()) {
                request.setAttribute("bookings", bookings);
            } else {
                request.setAttribute("errorMessage", "No bookings found.");
            }
        } else {
            // Otherwise, if bookingNumber is provided, retrieve its full details.
            String bookingNumber = request.getParameter("bookingNumber");
            if (bookingNumber != null && !bookingNumber.trim().isEmpty()) {
                Booking booking = bookingService.getBooking(bookingNumber);
                request.setAttribute("booking", booking);
            } else {
                request.setAttribute("errorMessage", "Booking number is required to view booking details.");
            }
        }
        // Forward the request to displayingBookings.jsp to render either the list or details.
        request.getRequestDispatcher("displayingBookings.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to add a new booking.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve booking details from the request.
        String bookingNumber = request.getParameter("bookingNumber");
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("customerAddress");
        String telephoneNumber = request.getParameter("telephoneNumber");
        String destination = request.getParameter("destination");
        String bookingDateStr = request.getParameter("bookingDate");
        
        // Parse booking date or use current date if not provided.
        Date bookingDate = null;
        if (bookingDateStr != null && !bookingDateStr.trim().isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                bookingDate = sdf.parse(bookingDateStr);
            } catch (Exception ex) {
                bookingDate = new Date();
            }
        } else {
            bookingDate = new Date();
        }

        // Build a Booking object using the Builder pattern.
        Booking booking = new Booking.Builder(bookingNumber)
                .customerName(customerName)
                .customerAddress(customerAddress)
                .telephoneNumber(telephoneNumber)
                .destination(destination)
                .bookingDate(bookingDate)
                .build();

        // Delegate the creation of the booking to BookingService.
        boolean isAdded = bookingService.addBooking(booking);
        if (isAdded) {
            request.setAttribute("message", "Booking added successfully.");
        } else {
            request.setAttribute("errorMessage", "Failed to add booking.");
        }
        // Forward to booking.jsp (or another view) for user feedback.
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }
}
