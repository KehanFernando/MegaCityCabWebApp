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
 * It retrieves the booking number from the client request, and if only the
 * booking number is provided (no distance), it will just load the basic
 * booking details without calculating fare. Once the user submits a manual
 * distance, the servlet calculates the total fare and shows the breakdown.
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
            // Check if a manual distance value has been provided.
            String manualDistanceStr = request.getParameter("manualDistance");

            if (manualDistanceStr != null && !manualDistanceStr.trim().isEmpty()) {
                // The user has submitted a distance for calculation
                try {
                    double manualDistance = Double.parseDouble(manualDistanceStr);
                    billingInfo = billingService.calculateBill(bookingNumber, manualDistance);
                    if (billingInfo == null) {
                        request.setAttribute("errorMessage", "No booking found for: " + bookingNumber);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid distance value provided.");
                }
            } else {
                // No manual distance yet, just load basic booking info (distance=0, total=0)
                billingInfo = billingService.getBookingDetails(bookingNumber);
                if (billingInfo == null) {
                    request.setAttribute("errorMessage", "Booking not found for booking number: " + bookingNumber);
                }
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
