package com.megacitycab.service;

import com.megacitycab.dao.DriverDAO;
import com.megacitycab.model.Driver;

/**
 * Service layer for Driver-related business logic.
 */
public class DriverService {

    private DriverDAO driverDAO;

    public DriverService() {
        driverDAO = new DriverDAO();
    }

    /**
     * Registers a new driver.
     *
     * @param driver the driver object to register.
     * @return true if registration was successful; false otherwise.
     */
    public boolean registerDriver(Driver driver) {
        // Additional business logic can be added here (e.g., validations)
        boolean isRegistered = driverDAO.insertDriver(driver);
        
        // If the driver is registered and an assignedCarId exists,
        // update the corresponding vehicle's driverId using vehicleRegId.
        if(isRegistered && driver.getAssignedCarId() != null && !driver.getAssignedCarId().trim().isEmpty()){
            driverDAO.updateVehicleDriver(driver.getAssignedCarId(), driver.getDriverId());
        }
        
        return isRegistered;
    }
}
