package com.megacitycab.controller;

import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;
import com.megacitycab.model.Car;
import com.megacitycab.service.ViewEditService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Servlet for handling view and edit operations for Customer, Driver and Vehicle details.
 */
@WebServlet("/ViewEditServlet")
public class ViewEditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ViewEditService service;

    @Override
    public void init() throws ServletException {
        service = new ViewEditService();
    }

    /**
     * Handles GET requests: Retrieves current details using the primary keys and forwards to viewEdt.jsp.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String registrationNumber = request.getParameter("registrationNumber");
        String driverId = request.getParameter("driverId");
        String vehicleRegId = request.getParameter("vehicleRegId");

        // Retrieve existing details from service layer
        Customer customer = service.getCustomer(registrationNumber);
        Driver driver = service.getDriver(driverId);
        Car car = service.getCar(vehicleRegId);

        request.setAttribute("customer", customer);
        request.setAttribute("driver", driver);
        request.setAttribute("car", car);

        request.getRequestDispatcher("viewEdt.jsp").forward(request, response);
    }

    /**
     * Handles POST requests: Receives edited fields and updates the records.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get primary keys (non-editable)
        String registrationNumber = request.getParameter("registrationNumber");
        String driverId = request.getParameter("driverId");
        String vehicleRegId = request.getParameter("vehicleRegId");

        // Get editable Customer fields
        String customerName = request.getParameter("customerName");
        String customerAddress = request.getParameter("customerAddress");
        String customerNic = request.getParameter("customerNic");
        String customerTelephone = request.getParameter("customerTelephone");

        // Get editable Driver fields
        String driverName = request.getParameter("driverName");
        String licenseNumber = request.getParameter("licenseNumber");
        String phone = request.getParameter("phone");
        String driverAddress = request.getParameter("driverAddress");
        String assignedCarId = request.getParameter("assignedCarId");

        // Get editable Vehicle fields
        String vehicleType = request.getParameter("vehicleType");
        String licensePlate = request.getParameter("licensePlate");
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");
        String color = request.getParameter("color");
        int seatingCapacity = 0;
        try {
            seatingCapacity = Integer.parseInt(request.getParameter("seatingCapacity"));
        } catch (NumberFormatException e) {
            // Optionally handle error if seating capacity is invalid
        }

        // Build updated objects using Builder patterns
        Customer updatedCustomer = new Customer.Builder(registrationNumber)
                .name(customerName)
                .address(customerAddress)
                .nic(customerNic)
                .telephone(customerTelephone)
                .build();

        Driver updatedDriver = new Driver.Builder(driverId)
                .name(driverName)
                .licenseNumber(licenseNumber)
                .phone(phone)
                .address(driverAddress)
                .assignedCarId(assignedCarId)
                .build();

        Car updatedCar = new Car.Builder(vehicleType, vehicleRegId)
                .licensePlate(licensePlate)
                .model(model)
                .brand(brand)
                .color(color)
                .seatingCapacity(seatingCapacity)
                .build();

        // Update each record via service layer
        boolean customerUpdated = service.updateCustomer(updatedCustomer);
        boolean driverUpdated = service.updateDriver(updatedDriver);
        boolean vehicleUpdated = service.updateCar(updatedCar);

        // Set message attributes
        if(customerUpdated && driverUpdated && vehicleUpdated) {
            request.setAttribute("message", "Records updated successfully!");
        } else {
            request.setAttribute("errorMessage", "One or more updates failed. Please try again.");
        }

        // Retrieve the updated records to show on the page
        request.setAttribute("customer", service.getCustomer(registrationNumber));
        request.setAttribute("driver", service.getDriver(driverId));
        request.setAttribute("car", service.getCar(vehicleRegId));

        request.getRequestDispatcher("viewEdt.jsp").forward(request, response);
    }
}
