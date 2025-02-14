package com.megacitycab.controller;

import com.megacitycab.service.BillingService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BillingServlet acts as the controller for handling billing-related requests.
 * <p>
 * It retrieves the booking number from the client request, delegates the calculation
 * of the bill to the BillingService, and forwards the result to a view (JSP) for display.
 * This design adheres to the MVC pattern and SOLID principles by separating presentation,
 * business logic, and data access concerns.
 * </p>
 */
public class BillingServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    // Singleton instance of the BillingService used for billing calculations.
    private BillingService billingService;

    @Override
    public void init() throws ServletException {
        super.init();
        // Obtain the singleton instance of BillingService.
        billingService = BillingService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /**
     * Processes both GET and POST requests.
     *
     * @param request  the HttpServletRequest object that contains the request from the client.
     * @param response the HttpServletResponse object that contains the response to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException      if an I/O error occurs.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve the booking number from request parameters.
        String bookingNumber = request.getParameter("bookingNumber");

        // Validate that the booking number is provided.
        double totalBill = 0.0;
        if (bookingNumber != null && !bookingNumber.trim().isEmpty()) {
            // Delegate billing calculation to the BillingService.
            totalBill = billingService.calculateBill(bookingNumber);
        } else {
            // Optionally, you might want to handle cases where booking number is missing.
            request.setAttribute("errorMessage", "Booking number is required.");
        }
        
        // Set attributes for use in the view (JSP).
        request.setAttribute("bookingNumber", bookingNumber);
        request.setAttribute("totalBill", totalBill);
        
        // Forward the request to billing.jsp to display the result.
        request.getRequestDispatcher("billing.jsp").forward(request, response);
    }
}
