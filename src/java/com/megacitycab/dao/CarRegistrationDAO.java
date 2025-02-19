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
}
