package com.megacitycab.controller;

import com.megacitycab.model.Car;
import com.megacitycab.model.Customer;
import com.megacitycab.model.Driver;
import com.megacitycab.service.ViewEditService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ViewEditServlet")
public class ViewEditServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ViewEditService service;

    @Override
    public void init() throws ServletException {
        service = new ViewEditService();
    }

    /**
     * Handles GET requests for searching records.
     * It checks the searchId parameter and determines if it is a customer, driver, or vehicle.
     * Additionally, if the searchId does not match known prefixes, it assumes a NIC search for customers.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchId = request.getParameter("searchId");

        if (searchId != null && !searchId.trim().isEmpty()) {
            try {
                // Check for customer registration number (CUS_ prefix)
                if (searchId.startsWith("CUS_")) {
                    Customer customer = service.getCustomer(searchId);
                    if (customer != null) {
                        request.setAttribute("recordType", "customer");
                        request.setAttribute("customer", customer);
                    } else {
                        request.setAttribute("errorMessage", "No customer record found for Registration Number " + searchId);
                    }
                }
                // Check for driver record (DI prefix)
                else if (searchId.startsWith("DI")) {
                    Driver driver = service.getDriver(searchId);
                    if (driver != null) {
                        request.setAttribute("recordType", "driver");
                        request.setAttribute("driver", driver);
                    } else {
                        request.setAttribute("errorMessage", "No driver record found for ID " + searchId);
                    }
                }
                // Check for vehicle record (prefixes: C, S, V, or B)
                else if (searchId.startsWith("C") || searchId.startsWith("S") ||
                         searchId.startsWith("V") || searchId.startsWith("B")) {
                    Car car = service.getCar(searchId);
                    if (car != null) {
                        request.setAttribute("recordType", "vehicle");
                        request.setAttribute("car", car);
                    } else {
                        request.setAttribute("errorMessage", "No vehicle record found for ID " + searchId);
                    }
                }
                // Otherwise, assume search is by NIC (for customers)
                else {
                    Customer customer = service.getCustomerByNic(searchId);
                    if (customer != null) {
                        request.setAttribute("recordType", "customer");
                        request.setAttribute("customer", customer);
                    } else {
                        request.setAttribute("errorMessage", "No customer record found for NIC " + searchId);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error retrieving record: " + e.getMessage());
            }
        }
        request.getRequestDispatcher("viewEdt.jsp").forward(request, response);
    }

    /**
     * Handles POST requests for updating or deleting records.
     * It determines which type of record is being updated or deleted based on the submitted parameters.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean updateSuccess = false;
        boolean deleteSuccess = false;
        String recordType = "";

        try {
            // Check for delete actions first
            if (request.getParameter("deleteCustomer") != null) {
                String registrationNumber = request.getParameter("registrationNumber");
                deleteSuccess = service.deleteCustomer(registrationNumber);
                recordType = "customer";
                if (deleteSuccess) {
                    request.setAttribute("message", "Customer account deleted successfully!");
                } else {
                    request.setAttribute("errorMessage", "Customer deletion failed.");
                }
            } else if (request.getParameter("deleteDriver") != null) {
                String driverId = request.getParameter("driverId");
                deleteSuccess = service.deleteDriver(driverId);
                recordType = "driver";
                if (deleteSuccess) {
                    request.setAttribute("message", "Driver account deleted successfully!");
                } else {
                    request.setAttribute("errorMessage", "Driver deletion failed.");
                }
            } else if (request.getParameter("deleteVehicle") != null) {
                String vehicleRegId = request.getParameter("vehicleRegId");
                deleteSuccess = service.deleteCar(vehicleRegId);
                recordType = "vehicle";
                if (deleteSuccess) {
                    request.setAttribute("message", "Vehicle removed successfully!");
                } else {
                    request.setAttribute("errorMessage", "Vehicle removal failed.");
                }
            }
            // If not a delete action, then process updates
            else if (request.getParameter("registrationNumber") != null && !request.getParameter("registrationNumber").isEmpty()) {
                // Update Customer record
                String registrationNumber = request.getParameter("registrationNumber");
                String customerName = request.getParameter("customerName");
                String customerAddress = request.getParameter("customerAddress");
                String customerNic = request.getParameter("customerNic");
                String customerTelephone = request.getParameter("customerTelephone");

                Customer updatedCustomer = new Customer.Builder(registrationNumber)
                        .name(customerName)
                        .address(customerAddress)
                        .nic(customerNic)
                        .telephone(customerTelephone)
                        .build();

                updateSuccess = service.updateCustomer(updatedCustomer);
                recordType = "customer";
                request.setAttribute("customer", service.getCustomer(registrationNumber));
                if (updateSuccess) {
                    request.setAttribute("message", "Customer record updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "Customer record update failed.");
                }
            } else if (request.getParameter("driverId") != null && !request.getParameter("driverId").isEmpty()) {
                // Update Driver record
                String driverId = request.getParameter("driverId");
                String driverName = request.getParameter("driverName");
                String licenseNumber = request.getParameter("licenseNumber");
                String phone = request.getParameter("phone");
                String driverAddress = request.getParameter("driverAddress");
                String assignedCarId = request.getParameter("assignedCarId");

                Driver updatedDriver = new Driver.Builder(driverId)
                        .name(driverName)
                        .licenseNumber(licenseNumber)
                        .phone(phone)
                        .address(driverAddress)
                        .assignedCarId(assignedCarId)
                        .build();

                updateSuccess = service.updateDriver(updatedDriver);
                recordType = "driver";
                request.setAttribute("driver", service.getDriver(driverId));
                if (updateSuccess) {
                    request.setAttribute("message", "Driver record updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "Driver record update failed.");
                }
            } else if (request.getParameter("vehicleRegId") != null && !request.getParameter("vehicleRegId").isEmpty()) {
                // Update Vehicle record
                String vehicleRegId = request.getParameter("vehicleRegId");
                String vehicleType = request.getParameter("vehicleType");
                
                // Validate vehicleType parameter
                if (vehicleType == null || vehicleType.trim().isEmpty()) {
                    request.setAttribute("errorMessage", "Vehicle type cannot be null or empty.");
                    request.setAttribute("recordType", "vehicle");
                    request.setAttribute("car", service.getCar(vehicleRegId));
                    request.getRequestDispatcher("viewEdt.jsp").forward(request, response);
                    return;
                }
                
                String licensePlate = request.getParameter("licensePlate");
                String model = request.getParameter("model");
                String brand = request.getParameter("brand");
                String color = request.getParameter("color");
                int seatingCapacity = 0;
                try {
                    seatingCapacity = Integer.parseInt(request.getParameter("seatingCapacity"));
                } catch (NumberFormatException e) {
                    // Handle invalid seating capacity input if needed.
                }
                String vehicleDriverId = request.getParameter("vehicleDriverId");

                Car updatedCar = new Car.Builder(vehicleType, vehicleRegId)
                        .licensePlate(licensePlate)
                        .model(model)
                        .brand(brand)
                        .color(color)
                        .seatingCapacity(seatingCapacity)
                        .driverId(vehicleDriverId)
                        .build();

                updateSuccess = service.updateCar(updatedCar);
                recordType = "vehicle";
                request.setAttribute("car", service.getCar(vehicleRegId));
                if (updateSuccess) {
                    request.setAttribute("message", "Vehicle record updated successfully!");
                } else {
                    request.setAttribute("errorMessage", "Vehicle record update failed.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error processing request: " + e.getMessage());
        }

        request.setAttribute("recordType", recordType);
        request.getRequestDispatcher("viewEdt.jsp").forward(request, response);
    }
}
