package com.megacitycab.model;

import java.util.Objects;

/**
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

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance containing car data.
     */
    private Car(Builder builder) {
        this.carId = builder.carId;
        this.licensePlate = builder.licensePlate;
        this.model = builder.model;
        this.brand = builder.brand;
        this.color = builder.color;
        this.seatingCapacity = builder.seatingCapacity;
    }

    // Getters for all fields (no setters to preserve immutability)
    public String getCarId() {
        return carId;
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

    @Override
    public String toString() {
        return "Car{" +
                "carId='" + carId + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", model='" + model + '\'' +
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", seatingCapacity=" + seatingCapacity +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Car)) return false;
        Car other = (Car) obj;
        return Objects.equals(this.carId, other.carId);
    }

    /**
     * Builder class for constructing Car instances.
     * <p>
     * This follows the Builder design pattern, providing a clear and flexible
     * approach to constructing immutable Car objects.
     */
    public static class Builder {
        // Required attribute
        private final String carId;
        // Optional attributes
        private String licensePlate;
        private String model;
        private String brand;
        private String color;
        private int seatingCapacity;

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
