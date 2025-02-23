package com.megacitycab.model;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a cab booking in the Mega City Cab system.
 * This class is implemented using the Builder design pattern.
 */
public class Booking {
    private final String bookingNumber;
    private final String customerName;
    private final String customerAddress;
    private final String telephoneNumber;
    private final String destination;
    private final Date bookingDate;
    private final String customerRegNo;

    private Booking(Builder builder) {
        this.bookingNumber = builder.bookingNumber;
        this.customerName = builder.customerName;
        this.customerAddress = builder.customerAddress;
        this.telephoneNumber = builder.telephoneNumber;
        this.destination = builder.destination;
        this.bookingDate = builder.bookingDate != null ? new Date(builder.bookingDate.getTime()) : null;
        this.customerRegNo = builder.customerRegNo;
    }

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
    
    // Correct getter name matching the SQL and usage in DAO.
    public String getCustomerRegNo() {
        return customerRegNo;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNumber='" + bookingNumber + '\'' +
                ", customerRegNo='" + customerRegNo + '\'' +
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

    public static class Builder {
        private final String bookingNumber;
        private String customerName;
        private String customerAddress;
        private String telephoneNumber;
        private String destination;
        private Date bookingDate;
        private String customerRegNo;

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
        
        public Builder customerRegNo(String customerRegNo) {
            this.customerRegNo = customerRegNo;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
