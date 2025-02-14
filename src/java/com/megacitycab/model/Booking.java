package com.megacitycab.model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a cab booking in the Mega City Cab system.
 * <p>
 * This class is implemented using the Builder design pattern to ensure immutability
 * and to facilitate the creation of Booking objects in a clear and controlled manner.
 */

public class Booking {
    // Core attributes for a booking
    private final String bookingNumber;
    private final String customerName;
    private final String customerAddress;
    private final String telephoneNumber;
    private final String destination;
    private final Date bookingDate;

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance with set properties.
     */
    private Booking(Builder builder) {
        this.bookingNumber = builder.bookingNumber;
        this.customerName = builder.customerName;
        this.customerAddress = builder.customerAddress;
        this.telephoneNumber = builder.telephoneNumber;
        this.destination = builder.destination;
        this.bookingDate = builder.bookingDate != null ? new Date(builder.bookingDate.getTime()) : null;
    }

    // Getters for all fields (no setters to preserve immutability)
    public String getBookingNumber() {
        return bookingNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getDestination() {
        return destination;
    }

    public Date getBookingDate() {
        return bookingDate != null ? new Date(bookingDate.getTime()) : null;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", destination='" + destination + '\'' +
                ", bookingDate=" + bookingDate +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Booking)) return false;
        Booking other = (Booking) obj;
        return Objects.equals(bookingNumber, other.bookingNumber);
    }

    /**
     * Builder class for constructing Booking instances.
     * <p>
     * This follows the Builder design pattern to create Booking objects
     * in a step-by-step manner while ensuring that required fields are provided.
     */
    public static class Builder {
        // Required attribute (assumed mandatory)
        private final String bookingNumber;
        // Optional attributes
        private String customerName;
        private String customerAddress;
        private String telephoneNumber;
        private String destination;
        private Date bookingDate;

        /**
         * Builder constructor with required booking number.
         *
         * @param bookingNumber the unique booking number (must not be null or empty)
         */
        public Builder(String bookingNumber) {
            if (bookingNumber == null || bookingNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Booking number cannot be null or empty.");
            }
            this.bookingNumber = bookingNumber;
        }

        public Builder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder customerAddress(String customerAddress) {
            this.customerAddress = customerAddress;
            return this;
        }

        public Builder telephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
            return this;
        }

        public Builder destination(String destination) {
            this.destination = destination;
            return this;
        }

        public Builder bookingDate(Date bookingDate) {
            this.bookingDate = bookingDate != null ? new Date(bookingDate.getTime()) : null;
            return this;
        }

        /**
         * Constructs a Booking instance with the provided values.
         *
         * @return a new Booking object.
         */
        public Booking build() {
            // Additional validation can be done here if needed.
            return new Booking(this);
        }
    }
}
