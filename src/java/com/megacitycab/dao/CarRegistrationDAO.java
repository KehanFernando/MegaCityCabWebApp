package com.megacitycab.dao;

import com.megacitycab.model.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRegistrationDAO {

    /**
     * Inserts a new vehicle record into the vehicles table.
     * @return number of rows affected.
     */
    public int insertVehicle(String vehicleType, String vehicleRegId, String licensePlate,
                             String model, String brand, String color, int seatingCapacity) {
        int rowsAffected = 0;
        String sql = "INSERT INTO vehicles (vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            stmt.setString(2, vehicleRegId);
            stmt.setString(3, licensePlate);
            stmt.setString(4, model);
            stmt.setString(5, brand);
            stmt.setString(6, color);
            stmt.setInt(7, seatingCapacity);
            
            rowsAffected = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Optionally log the exception or rethrow as a custom exception
        }
        return rowsAffected;
    }
    
    /**
     * Retrieves a vehicle from the database based on vehicleRegId.
     */
    public Car getCar(String vehicleRegId) throws Exception {
        String sql = "SELECT vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity, driverId FROM vehicles WHERE vehicleRegId = ?";
        Car car = null;
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleRegId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    car = new Car.Builder(rs.getString("vehicleType"), rs.getString("vehicleRegId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .model(rs.getString("model"))
                            .brand(rs.getString("brand"))
                            .color(rs.getString("color"))
                            .seatingCapacity(rs.getInt("seatingCapacity"))
                            .driverId(rs.getString("driverId")) // NEW: set driverId from vehicles table
                            .build();
                }
            }
        }
        return car;
    }

    /**
     * Updates an existing vehicle record.
     */
    public boolean updateVehicle(Car car) throws Exception {
        String sql = "UPDATE vehicles SET vehicleType = ?, licensePlate = ?, model = ?, brand = ?, color = ?, seatingCapacity = ?, driverId = ? WHERE vehicleRegId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getVehicleType());
            stmt.setString(2, car.getLicensePlate());
            stmt.setString(3, car.getModel());
            stmt.setString(4, car.getBrand());
            stmt.setString(5, car.getColor());
            stmt.setInt(6, car.getSeatingCapacity());
            stmt.setString(7, car.getDriverId()); // NEW: update driverId column
            stmt.setString(8, car.getVehicleRegId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    
    /**
     * Retrieves an available vehicle for the given vehicle type.
     *
     * @param vehicleType The type of the vehicle.
     * @return A Car object if found, otherwise null.
     * @throws Exception
     */
    public Car getAvailableVehicle(String vehicleType) throws Exception {
        String sql = "SELECT vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity, driverId "
                   + "FROM vehicles WHERE vehicleType = ? LIMIT 1";
        Car car = null;
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setString(1, vehicleType);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    car = new Car.Builder(rs.getString("vehicleType"), rs.getString("vehicleRegId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .model(rs.getString("model"))
                            .brand(rs.getString("brand"))
                            .color(rs.getString("color"))
                            .seatingCapacity(rs.getInt("seatingCapacity"))
                            .driverId(rs.getString("driverId"))
                            .build();
                }
            }
        }
        return car;
    }
    
    /**
     * Retrieves an available vehicle for the given vehicle type that is not already booked.
     * The query excludes vehicles whose vehicleRegId exists in the bookings table.
     *
     * @param vehicleType The type of the vehicle.
     * @return A Car object if found, otherwise null.
     * @throws Exception
     */
    public Car getAvailableNotBookedVehicle(String vehicleType) throws Exception {
        String sql = "SELECT vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity, driverId " +
                     "FROM vehicles " +
                     "WHERE vehicleType = ? " +
                     "AND vehicleRegId NOT IN (SELECT vehicleRegId FROM bookings WHERE vehicleType = ?) " +
                     "LIMIT 1";
        Car car = null;
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, vehicleType);
            stmt.setString(2, vehicleType);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    car = new Car.Builder(rs.getString("vehicleType"), rs.getString("vehicleRegId"))
                            .licensePlate(rs.getString("licensePlate"))
                            .model(rs.getString("model"))
                            .brand(rs.getString("brand"))
                            .color(rs.getString("color"))
                            .seatingCapacity(rs.getInt("seatingCapacity"))
                            .driverId(rs.getString("driverId"))
                            .build();
                }
            }
        }
        return car;
    }
}
