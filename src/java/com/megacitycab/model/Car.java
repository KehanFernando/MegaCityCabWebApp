package com.megacitycab.model;

import java.util.Objects;

/**
<<<<<<< HEAD
 * Represents a car in the Mega City Cab system.
 * <p>
 * This class is designed using the Builder pattern to enforce immutability and
 * provide a clear, flexible construction process for Car objects.
 * <p>
 * Adheres to the Single Responsibility Principle by solely representing car data.
 */

public class Car {
    // Core attributes for a Car
    private final String carId;         // Unique identifier for the car
    private final String licensePlate;  // License plate number
    private final String model;         // Model of the car
    private final String brand;         // Brand or manufacturer
    private final String color;         // Color of the car
    private final int seatingCapacity;  // Number of seats available
=======
 * Represents a vehicle in the Mega City Cab system.
 * 
 * This class is designed using the Builder pattern to enforce immutability and
 * provide a clear, flexible construction process for Vehicle objects.
 * 
 * Adheres to the Single Responsibility Principle by solely representing vehicle data.
 */
public class Car {
    // Core attributes for a Vehicle
    private final String vehicleType;     // Type of the vehicle (Car, SUV, Van, Bus)
    private final String vehicleRegId;    // Auto-generated unique registration ID
    private final String licensePlate;    // License plate number
    private final String model;           // Model of the vehicle
    private final String brand;           // Brand or manufacturer
    private final String color;           // Color of the vehicle
    private final int seatingCapacity;    // Number of seats available
    // NEW: driverId field from vehicles table
    private final String driverId;
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)

    /**
     * Private constructor to enforce object creation via the Builder.
     *
<<<<<<< HEAD
     * @param builder the Builder instance containing car data.
     */
    private Car(Builder builder) {
        this.carId = builder.carId;
=======
     * @param builder the Builder instance containing vehicle data.
     */
    private Car(Builder builder) {
        this.vehicleType = builder.vehicleType;
        this.vehicleRegId = builder.vehicleRegId;
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        this.licensePlate = builder.licensePlate;
        this.model = builder.model;
        this.brand = builder.brand;
        this.color = builder.color;
        this.seatingCapacity = builder.seatingCapacity;
<<<<<<< HEAD
    }

    // Getters for all fields (no setters to preserve immutability)
    public String getCarId() {
        return carId;
=======
        this.driverId = builder.driverId;
    }

    // Getters for all fields (no setters to preserve immutability)
    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleRegId() {
        return vehicleRegId;
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getModel() {
        return model;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }
<<<<<<< HEAD
=======
    
    // NEW: getter for driverId
    public String getDriverId() {
        return driverId;
    }
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)

    @Override
    public String toString() {
        return "Car{" +
<<<<<<< HEAD
                "carId='" + carId + '\'' +
=======
                "vehicleType='" + vehicleType + '\'' +
                ", vehicleRegId='" + vehicleRegId + '\'' +
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
                ", licensePlate='" + licensePlate + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", seatingCapacity=" + seatingCapacity +
<<<<<<< HEAD
=======
                ", driverId='" + driverId + '\'' +
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
                '}';
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        return Objects.hash(carId);
=======
        return Objects.hash(vehicleRegId);
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Car)) return false;
        Car other = (Car) obj;
<<<<<<< HEAD
        return Objects.equals(this.carId, other.carId);
=======
        return Objects.equals(this.vehicleRegId, other.vehicleRegId);
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    }

    /**
     * Builder class for constructing Car instances.
<<<<<<< HEAD
     * <p>
=======
     * 
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
     * This follows the Builder design pattern, providing a clear and flexible
     * approach to constructing immutable Car objects.
     */
    public static class Builder {
<<<<<<< HEAD
        // Required attribute
        private final String carId;
=======
        // Required attributes
        private final String vehicleType;
        private final String vehicleRegId;
        
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        // Optional attributes
        private String licensePlate;
        private String model;
        private String brand;
        private String color;
        private int seatingCapacity;
<<<<<<< HEAD

        /**
         * Builder constructor with the required carId.
         *
         * @param carId the unique identifier for the car (must not be null or empty)
         */
        public Builder(String carId) {
            if (carId == null || carId.trim().isEmpty()) {
                throw new IllegalArgumentException("Car ID cannot be null or empty.");
            }
            this.carId = carId;
=======
        // NEW: Optional driverId field
        private String driverId;

        /**
         * Builder constructor with the required fields.
         *
         * @param vehicleType  the type of the vehicle (must not be null or empty)
         * @param vehicleRegId the auto-generated vehicle registration ID (must not be null or empty)
         */
        public Builder(String vehicleType, String vehicleRegId) {
            if (vehicleType == null || vehicleType.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle type cannot be null or empty.");
            }
            if (vehicleRegId == null || vehicleRegId.trim().isEmpty()) {
                throw new IllegalArgumentException("Vehicle Registration ID cannot be null or empty.");
            }
            this.vehicleType = vehicleType;
            this.vehicleRegId = vehicleRegId;
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        }

        public Builder licensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public Builder model(String model) {
            this.model = model;
            return this;
        }

        public Builder brand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder color(String color) {
            this.color = color;
            return this;
        }

        public Builder seatingCapacity(int seatingCapacity) {
            this.seatingCapacity = seatingCapacity;
            return this;
        }
<<<<<<< HEAD
=======
        
        // NEW: method to set driverId
        public Builder driverId(String driverId) {
            this.driverId = driverId;
            return this;
        }
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)

        /**
         * Builds the Car instance with the provided values.
         *
         * @return a new Car object.
         */
        public Car build() {
            return new Car(this);
        }
    }
}
