package com.megacitycab.dao;

import com.megacitycab.model.Car;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        String sql = "SELECT vehicleType, vehicleRegId, licensePlate, model, brand, color, seatingCapacity FROM vehicles WHERE vehicleRegId = ?";
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
        String sql = "UPDATE vehicles SET vehicleType = ?, licensePlate = ?, model = ?, brand = ?, color = ?, seatingCapacity = ? WHERE vehicleRegId = ?";
        try (Connection conn = DBConnectionManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getVehicleType());
            stmt.setString(2, car.getLicensePlate());
            stmt.setString(3, car.getModel());
            stmt.setString(4, car.getBrand());
            stmt.setString(5, car.getColor());
            stmt.setInt(6, car.getSeatingCapacity());
            stmt.setString(7, car.getVehicleRegId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }

}
