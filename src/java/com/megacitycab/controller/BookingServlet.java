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
 */
public class BookingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private BookingService bookingService;

    @Override
    public void init() throws ServletException {
        super.init();
        bookingService = BookingService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("list".equalsIgnoreCase(action)) {
            List<Booking> bookings = bookingService.getAllBookings();
            if (bookings != null && !bookings.isEmpty()) {
                request.setAttribute("bookings", bookings);
            } else {
                request.setAttribute("errorMessage", "No bookings found.");
            }
        } else {
            String bookingNumber = request.getParameter("bookingNumber");
            if (bookingNumber != null && !bookingNumber.trim().isEmpty()) {
                Booking booking = bookingService.getBooking(bookingNumber);
                request.setAttribute("booking", booking);
            } else {
                request.setAttribute("errorMessage", "Booking number is required to view booking details.");
            }
        }
        request.getRequestDispatcher("displayingBookings.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookingNumber = request.getParameter("bookingNumber");
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("pickupLocation");
        String telephoneNumber = request.getParameter("telephoneNumber");
        String destination = request.getParameter("destination");
        String bookingDateStr = request.getParameter("bookingDate");
        String customerRegNo = request.getParameter("customerRegNo");
        
         // New vehicle fields from the form:
        String vehicleType = request.getParameter("vehicleType");
        String vehicleRegId = request.getParameter("vehicleRegId");
        String vbrand = request.getParameter("brand");
        String vmodel = request.getParameter("model");
        String vseating = request.getParameter("seatingCapacity");

        // Pre-insert validation (ensure required fields are provided)
        if (customerName == null || customerName.trim().isEmpty() ||
            telephoneNumber == null || telephoneNumber.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Customer details are missing. Please ensure the registration number is valid.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

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

        Booking booking = new Booking.Builder(bookingNumber)
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

        boolean isAdded = bookingService.addBooking(booking);
        if (isAdded) {
            request.setAttribute("message", "Booking added successfully.");
        } else {
            request.setAttribute("errorMessage", "Failed to add booking.");
        }
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }
}
