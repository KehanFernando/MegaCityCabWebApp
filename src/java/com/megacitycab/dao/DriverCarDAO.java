package com.megacitycab.dao;

import com.megacitycab.model.Car;
import com.megacitycab.model.Driver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for handling driver-related operations in the database.
 * <p>
 * This class encapsulates all CRUD operations for Driver objects. It follows the DAO pattern,
 * separating data persistence logic from business logic, and is implemented as a Singleton to
 * ensure a single point of access for driver database operations.
 * </p>
 */

public class DriverCarDAO {
    // Singleton instance
    private static DriverCarDAO instance;

    // Private constructor to enforce the singleton pattern
    private DriverCarDAO() {}

    /**
     * Returns the singleton instance of DriverCarDAO.
     *
     * @return the singleton instance.
     */
    public static synchronized DriverCarDAO getInstance() {
        if (instance == null) {
            instance = new DriverCarDAO();
        }
        return instance;
    }

    // ---------------- Car related operations ----------------

    /**
     * Adds a new Car record to the database.
     *
     * @param car the Car object containing car details.
     * @return true if the car is added successfully; false otherwise.
     */
    public boolean addCar(Car car) {
        String sql = "INSERT INTO cars (carId, licensePlate, model, brand, color, seatingCapacity) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getCarId());
            stmt.setString(2, car.getLicensePlate());
            stmt.setString(3, car.getModel());
            stmt.setString(4, car.getBrand());
            stmt.setString(5, car.getColor());
            stmt.setInt(6, car.getSeatingCapacity());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.err.println("Error while adding car: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a Car record from the database based on the carId.
     *
     * @param carId the unique identifier of the car.
     * @return the Car object if found; otherwise, null.
     */
    public Car getCar(String carId) {
        String sql = "SELECT carId, licensePlate, model, brand, color, seatingCapacity "
                   + "FROM cars WHERE carId = ?";
        Car car = null;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, carId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("carId");
                    String licensePlate = rs.getString("licensePlate");
                    String model = rs.getString("model");
                    String brand = rs.getString("brand");
                    String color = rs.getString("color");
                    int seatingCapacity = rs.getInt("seatingCapacity");

                    // Build the Car object using the Builder pattern
                    car = new Car.Builder(id)
                            .licensePlate(licensePlate)
                            .model(model)
                            .brand(brand)
                            .color(color)
                            .seatingCapacity(seatingCapacity)
                            .build();
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while retrieving car: " + ex.getMessage());
            ex.printStackTrace();
        }
        return car;
    }

    // ---------------- Driver related operations ----------------

    /**
     * Adds a new Driver record to the database.
     *
     * @param driver the Driver object containing driver details.
     * @return true if the driver is added successfully; false otherwise.
     */
    public boolean addDriver(Driver driver) {
        String sql = "INSERT INTO drivers (driverId, name, licenseNumber, phone, address, assignedCarId) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.getDriverId());
            stmt.setString(2, driver.getName());
            stmt.setString(3, driver.getLicenseNumber());
            stmt.setString(4, driver.getPhone());
            stmt.setString(5, driver.getAddress());
            stmt.setString(6, driver.getAssignedCarId());  // This can be null if no car is assigned

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException ex) {
            System.err.println("Error while adding driver: " + ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Retrieves a Driver record from the database based on the driverId.
     *
     * @param driverId the unique identifier of the driver.
     * @return the Driver object if found; otherwise, null.
     */
    public Driver getDriver(String driverId) {
        String sql = "SELECT driverId, name, licenseNumber, phone, address, assignedCarId "
                   + "FROM drivers WHERE driverId = ?";
        Driver driver = null;

        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driverId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("driverId");
                    String name = rs.getString("name");
                    String licenseNumber = rs.getString("licenseNumber");
                    String phone = rs.getString("phone");
                    String address = rs.getString("address");
                    String assignedCarId = rs.getString("assignedCarId");

                    // Build the Driver object using the Builder pattern
                    driver = new Driver.Builder(id)
                            .name(name)
                            .licenseNumber(licenseNumber)
                            .phone(phone)
                            .address(address)
                            .assignedCarId(assignedCarId)
                            .build();
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error while retrieving driver: " + ex.getMessage());
            ex.printStackTrace();
        }
        return driver;
    }
}
