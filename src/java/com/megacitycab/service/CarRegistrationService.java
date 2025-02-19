package com.megacitycab.service;

import com.megacitycab.dao.CarRegistrationDAO;
import com.megacitycab.model.Car;
import java.sql.SQLException;
import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

public class CarRegistrationService {

    private CarRegistrationDAO dao;

    public CarRegistrationService() {
        dao = new CarRegistrationDAO();
    }

    /**
     * Registers a new vehicle using the provided details.
     * @return true if the vehicle was successfully registered, false otherwise.
     */
    public boolean registerVehicle(String vehicleType, String vehicleRegId, String licensePlate, 
                                   String model, String brand, String color, int seatingCapacity) {
        int result = dao.insertVehicle(vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity);
        return result > 0;
    }
}
