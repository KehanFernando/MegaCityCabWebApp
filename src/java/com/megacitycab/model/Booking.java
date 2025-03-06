package com.megacitycab.model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a cab booking in the Mega City Cab system.
<<<<<<< HEAD
 * <p>
 * This class is implemented using the Builder design pattern to ensure immutability
 * and to facilitate the creation of Booking objects in a clear and controlled manner.
 */

public class Booking {
    // Core attributes for a booking
=======
 * This class is implemented using the Builder design pattern.
 */
public class Booking {
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    private final String bookingNumber;
    private final String customerName;
    private final String customerAddress;
    private final String telephoneNumber;
    private final String destination;
    private final Date bookingDate;
<<<<<<< HEAD

    /**
     * Private constructor to enforce object creation via the Builder.
     *
     * @param builder the Builder instance with set properties.
     */
=======
    private final String customerRegNo;
    // New vehicle fields
    private final String vehicleType;
    private final String vehicleRegId;
    private final String vbrand;
    private final String vmodel;
    private final String vseating;

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
    private Booking(Builder builder) {
        this.bookingNumber = builder.bookingNumber;
        this.customerName = builder.customerName;
        this.customerAddress = builder.customerAddress;
        this.telephoneNumber = builder.telephoneNumber;
        this.destination = builder.destination;
        this.bookingDate = builder.bookingDate != null ? new Date(builder.bookingDate.getTime()) : null;
<<<<<<< HEAD
    }

    // Getters for all fields (no setters to preserve immutability)
=======
        this.customerRegNo = builder.customerRegNo;
        this.vehicleType = builder.vehicleType;
        this.vehicleRegId = builder.vehicleRegId;
        this.vbrand = builder.vbrand;
        this.vmodel = builder.vmodel;
        this.vseating = builder.vseating;
    }

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
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
<<<<<<< HEAD
=======
    
    public String getCustomerRegNo() {
        return customerRegNo;
    }
    
    // New getters for vehicle details
    public String getVehicleType() {
        return vehicleType;
    }

    public String getVehicleRegId() {
        return vehicleRegId;
    }

    public String getVbrand() {
        return vbrand;
    }

    public String getVmodel() {
        return vmodel;
    }

    public String getVseating() {
        return vseating;
    }
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNumber='" + bookingNumber + '\'' +
<<<<<<< HEAD
=======
                ", customerRegNo='" + customerRegNo + '\'' +
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                ", destination='" + destination + '\'' +
                ", bookingDate=" + bookingDate +
<<<<<<< HEAD
=======
                ", vehicleType='" + vehicleType + '\'' +
                ", vehicleRegId='" + vehicleRegId + '\'' +
                ", vbrand='" + vbrand + '\'' +
                ", vmodel='" + vmodel + '\'' +
                ", vseating='" + vseating + '\'' +
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
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

<<<<<<< HEAD
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
=======
    public static class Builder {
        private final String bookingNumber;
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
        private String customerName;
        private String customerAddress;
        private String telephoneNumber;
        private String destination;
        private Date bookingDate;
<<<<<<< HEAD

        /**
         * Builder constructor with required booking number.
         *
         * @param bookingNumber the unique booking number (must not be null or empty)
         */
=======
        private String customerRegNo;
        // New fields for vehicle details
        private String vehicleType;
        private String vehicleRegId;
        private String vbrand;
        private String vmodel;
        private String vseating;

>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
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
<<<<<<< HEAD

        /**
         * Constructs a Booking instance with the provided values.
         *
         * @return a new Booking object.
         */
        public Booking build() {
            // Additional validation can be done here if needed.
=======
        
        public Builder customerRegNo(String customerRegNo) {
            this.customerRegNo = customerRegNo;
            return this;
        }

        // New builder methods for vehicle details
        public Builder vehicleType(String vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder vehicleRegId(String vehicleRegId) {
            this.vehicleRegId = vehicleRegId;
            return this;
        }

        public Builder vbrand(String vbrand) {
            this.vbrand = vbrand;
            return this;
        }

        public Builder vmodel(String vmodel) {
            this.vmodel = vmodel;
            return this;
        }

        public Builder vseating(String vseating) {
            this.vseating = vseating;
            return this;
        }

        public Booking build() {
>>>>>>> f05e487 (Updated MegaCityCabWebApp project)
            return new Booking(this);
        }
    }
}
