package com.megacitycab.controller;

import com.megacitycab.model.Driver;
import com.megacitycab.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet controller for handling driver registration requests.
 */
@WebServlet("/DriverController")
public class DriverSevlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DriverService driverService;

    @Override
    public void init() throws ServletException {
        driverService = new DriverService();
    }

    /**
     * Processes registration form submissions.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters.
        String driverId = request.getParameter("driverId");
        String name = request.getParameter("name");
        String licenseNumber = request.getParameter("licenseNumber");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String assignedCarId = request.getParameter("assignedCarId");

        // Build a Driver instance using the Builder pattern.
        Driver driver = new Driver.Builder(driverId)
                .name(name)
                .licenseNumber(licenseNumber)
                .phone(phone)
                .address(address)
                .assignedCarId(assignedCarId)
                .build();

        // Attempt to register the driver.
        boolean isRegistered = driverService.registerDriver(driver);

        // Set a feedback message based on the outcome.
        if(isRegistered) {
            request.setAttribute("message", "Driver registered successfully!");
        } else {
            request.setAttribute("message", "Driver registration failed. Please try again.");
        }
        request.getRequestDispatcher("driverReg.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Redirect GET requests to the POST handler.
        doPost(request, response);
    }
}
