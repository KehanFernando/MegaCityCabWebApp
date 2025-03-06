package com.megacitycab.dao;

import com.megacitycab.model.Driver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * DAO for persisting Driver objects.
 */
public class DriverDAO {

    // SQL query for inserting a new driver.
    private static final String INSERT_DRIVER_SQL = "INSERT INTO drivers (driverId, name, licenseNumber, phone, address, assignedCarId) VALUES (?, ?, ?, ?, ?, ?)";

    /**
     * Inserts a new driver into the database.
     *
     * @param driver the driver object to insert.
     * @return true if the driver was successfully inserted; false otherwise.
     */
    public boolean insertDriver(Driver driver) {
        boolean rowInserted = false;
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_DRIVER_SQL)) {
            
            // Map the Driver object's properties to the SQL query.
            stmt.setString(1, driver.getDriverId());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getLicenseNumber());
            stmt.setString(4, driver.getPhone());
            stmt.setString(5, driver.getAddress());
            stmt.setString(6, driver.getAssignedCarId());
            
            rowInserted = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging the error and/or rethrowing as a custom exception.
        }
        return rowInserted;
    }

    /**
     * Updates the vehicles table by setting its driverId based on the provided vehicleRegId.
     *
     * @param vehicleRegId the vehicle's registration id to identify the record.
     * @param driverId the driver's id to be set in the vehicles table.
     * @return true if the vehicle record was successfully updated; false otherwise.
     */
    public boolean updateVehicleDriver(String vehicleRegId, String driverId) {
        boolean updated = false;
        String sql = "UPDATE vehicles SET driverId = ? WHERE vehicleRegId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, driverId);
            stmt.setString(2, vehicleRegId);
            updated = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Consider logging or handling the exception as needed.
        }
        return updated;
    }

    /**
     * Retrieves a driver from the database based on driverId.
     */
    public Driver getDriver(String driverId) throws Exception {
        String sql = "SELECT driverId, name, licenseNumber, phone, address, assignedCarId FROM drivers WHERE driverId = ?";
        Driver driver = null;
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, driverId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    driver = new Driver.Builder(rs.getString("driverId"))
                            .name(rs.getString("name"))
                            .licenseNumber(rs.getString("licenseNumber"))
                            .phone(rs.getString("phone"))
                            .address(rs.getString("address"))
                            .assignedCarId(rs.getString("assignedCarId"))
                            .build();
                }
            }
        }
        return driver;
    }

    /**
     * Updates an existing driver record.
     */
    public boolean updateDriver(Driver driver) throws Exception {
        String sql = "UPDATE drivers SET name = ?, licenseNumber = ?, phone = ?, address = ?, assignedCarId = ? WHERE driverId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.getName());
            stmt.setString(2, driver.getLicenseNumber());
            stmt.setString(3, driver.getPhone());
            stmt.setString(4, driver.getAddress());
            stmt.setString(5, driver.getAssignedCarId());
            stmt.setString(6, driver.getDriverId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    
    /**
     * Deletes a driver record from the database based on driverId.
     *
     * @param driverId the unique identifier for the driver.
     * @return true if deletion was successful; false otherwise.
     */
    public boolean deleteDriver(String driverId) {
        String sql = "DELETE FROM drivers WHERE driverId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, driverId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally log or rethrow as a custom exception
        }
        return false;
    }
}
