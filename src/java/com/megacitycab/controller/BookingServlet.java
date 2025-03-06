<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megacitycab.controller;

import java.io.IOException;
import java.io.PrintWriter;
=======
package com.megacitycab.controller;

import com.megacitycab.model.Booking;
import com.megacitycab.service.BookingService;
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD

/**
 *
 * @author Kehan Fernando
 */
public class BookingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BookingServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BookingServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

=======
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
            request.getRequestDispatcher("displayingBookings.jsp").forward(request, response);
        } else if ("delete".equalsIgnoreCase(action)) {
            String bookingNumber = request.getParameter("bookingNumber");
            if (bookingNumber != null && !bookingNumber.trim().isEmpty()) {
                boolean isDeleted = bookingService.deleteBooking(bookingNumber);
                if (isDeleted) {
                    request.setAttribute("message", "Booking deleted successfully.");
                } else {
                    request.setAttribute("errorMessage", "Failed to delete booking.");
                }
            } else {
                request.setAttribute("errorMessage", "Booking number is required to delete booking.");
            }
            // Refresh the list after deletion
            List<Booking> bookings = bookingService.getAllBookings();
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("displayingBookings.jsp").forward(request, response);
        } else {
            String bookingNumber = request.getParameter("bookingNumber");
            if (bookingNumber != null && !bookingNumber.trim().isEmpty()) {
                Booking booking = bookingService.getBooking(bookingNumber);
                request.setAttribute("booking", booking);
            } else {
                request.setAttribute("errorMessage", "Booking number is required to view booking details.");
            }
            request.getRequestDispatcher("displayingBookings.jsp").forward(request, response);
        }
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
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
}
