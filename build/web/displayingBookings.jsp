<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.megacitycab.model.Booking" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Booking Details</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset default styles */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f2f2f2;
            padding: 2rem;
        }
        /* Navigation styling */
        header.navbar {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            background: #fff;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
            padding: 1rem 2rem;
            display: flex;
            align-items: center;
            justify-content: space-between;
            z-index: 1000;
        }
        header.navbar .brand {
            font-size: 1.2rem;
            font-weight: bold;
            color: #333;
            text-decoration: none;
        }
        header.navbar .nav-links {
            display: flex;
            gap: 1rem;
        }
        header.navbar .nav-links a {
            color: #5563DE;
            text-decoration: none;
            font-size: 1rem;
            transition: color 0.3s;
        }
        header.navbar .nav-links a:hover {
            color: #444;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            margin-top: 50px;
        }
        h1 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }
        .booking-details, .booking-card {
            margin-bottom: 1.5rem;
        }
        .booking-details p {
            margin-bottom: 0.75rem;
            line-height: 1.6;
            font-size: 1rem;
            color: #555;
        }
        .label {
            font-weight: bold;
            color: #333;
        }
        .back-link {
            display: block;
            text-align: center;
            margin-top: 2rem;
            text-decoration: none;
            font-size: 1rem;
            color: #5563DE;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #444;
        }
        /* Styling for booking preview cards */
        .booking-card {
            background: #e8f0fe;
            padding: 1rem;
            border-radius: 6px;
            margin-bottom: 1rem;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .booking-card:hover {
            background: #d0e1fd;
        }
        .booking-info {
            display: flex;
            justify-content: space-between;
        }
        .booking-info span {
            font-size: 1rem;
            color: #333;
        }
    </style>
</head>
<body>
    <!-- Navigation Header -->
    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
        <nav class="nav-links">
            <a href="booking.jsp">New Booking</a>
            <!-- To view list, pass action=list -->
            <a href="BookingServlet?action=list">View Bookings</a>
            <a href="register.jsp">Registration</a>
            <a href="billing.jsp">Billing</a>
            <a href="help.jsp">Help</a>
            <a href="dashboard.jsp">Dashboard</a>
        </nav>
    </header>
 
<div class="container">
    <%
        // Check if a list of bookings was provided.
        List<Booking> bookings = (List<Booking>) request.getAttribute("bookings");
        Booking booking = (Booking) request.getAttribute("booking");
        String errorMessage = (String) request.getAttribute("errorMessage");
        
        if (errorMessage != null) {
    %>
        <p style="text-align:center; color:red;"><%= errorMessage %></p>
    <%
        } else if (bookings != null) { 
    %>
        <h1>Bookings List</h1>
        <% for (Booking b : bookings) { %>
            <!-- Each card is clickable. Clicking sends the bookingNumber to BookingServlet for full details -->
            <a href="BookingServlet?bookingNumber=<%= b.getBookingNumber() %>" style="text-decoration:none;">
                <div class="booking-card">
                    <div class="booking-info">
                        <span><strong>Booking Number:</strong> <%= b.getBookingNumber() %></span>
                        <span><strong>Customer Name:</strong> <%= b.getCustomerName() %></span>
                    </div>
                </div>
            </a>
        <% } %>
    <%
        } else if (booking != null) { 
    %>
        <h1>Booking Details</h1>
        <div class="booking-details">
            <p><span class="label">Booking Number:</span> <%= booking.getBookingNumber() %></p>
            <p><span class="label">Customer Name:</span> <%= booking.getCustomerName() %></p>
            <p><span class="label">Customer Address:</span> <%= booking.getCustomerAddress() %></p>
            <p><span class="label">Telephone Number:</span> <%= booking.getTelephoneNumber() %></p>
            <p><span class="label">Destination:</span> <%= booking.getDestination() %></p>
            <p>
            <span class="label">Booking Date:</span>
            <%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(booking.getBookingDate()) %>
            </p>

        </div>
    <%
        } else {
    %>
        <p style="text-align:center; color:red;">No booking details found.</p>
    <%
        }
    %>
    <a class="back-link" href="BookingServlet?action=list">Return to Back</a>
</div>
</body>
</html>
