package com.megacitycab.controller;

import com.megacitycab.service.BillingService;
import com.megacitycab.service.BillingService.BillingInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BillingServlet acts as the controller for handling billing-related requests.
 * <p>
 * It retrieves the booking number from the client request, delegates the calculation
 * of the billing details to the BillingService, and forwards the result to a view (billing.jsp)
 * for display.
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

        BillingInfo billingInfo = null;
        if (bookingNumber != null && !bookingNumber.trim().isEmpty()) {
            // Delegate billing calculation to the BillingService.
            billingInfo = billingService.calculateBill(bookingNumber);
            if (billingInfo == null) {
                request.setAttribute("errorMessage", "Booking not found for booking number: " + bookingNumber);
            }
        } else {
            // Handle cases where booking number is missing.
            request.setAttribute("errorMessage", "Booking number is required.");
        }

        // Set the billing info attribute for use in the view (JSP).
        request.setAttribute("billingInfo", billingInfo);

        // Forward the request to billing.jsp to display the result.
        request.getRequestDispatcher("billing.jsp").forward(request, response);
    }
}
