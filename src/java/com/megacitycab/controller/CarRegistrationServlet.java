package com.megacitycab.controller;

import com.megacitycab.service.CarRegistrationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/CarRegistrationServlet")
public class CarRegistrationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CarRegistrationService service;

    @Override
    public void init() throws ServletException {
        service = new CarRegistrationService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the form
        String vehicleType = request.getParameter("vehicleType");
        String vehicleRegId = request.getParameter("vehicleRegId");
        String licensePlate = request.getParameter("licensePlate");
        String model = request.getParameter("model");
        String brand = request.getParameter("brand");
        String color = request.getParameter("color");
        int seatingCapacity = 0;
        try {
            seatingCapacity = Integer.parseInt(request.getParameter("seatingCapacity"));
        } catch (NumberFormatException e) {
            // Handle if seatingCapacity is not provided or is invalid
        }

        // Call service to register the vehicle
        boolean isRegistered = service.registerVehicle(vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity);

        if(isRegistered){
            request.setAttribute("message", "Vehicle registered successfully with Registration ID: " + vehicleRegId);
        } else {
            request.setAttribute("errorMessage", "Vehicle registration failed. Please try again.");
        }
        request.getRequestDispatcher("carReg.jsp").forward(request, response);
    }
}
