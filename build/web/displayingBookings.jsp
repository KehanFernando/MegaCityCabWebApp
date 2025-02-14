<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        .booking-details {
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
    </style>
</head>
<body>
 <!-- Navigation Header -->
    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
        <nav class="nav-links">
            <a href="booking.jsp">New Booking</a>
            <a href="displayingBookings.jsp">View Bookings</a>
            <a href="billing.jsp">Billing</a>
            <a href="help.jsp">Help</a>
            <a href="dashboard.jsp">Dashboard</a>
        </nav>
    </header>
 
<div class="container">
    <h1>Booking Details</h1>
    <% 
        // Retrieve the booking object passed from the BookingServlet
        com.megacitycab.model.Booking booking = (com.megacitycab.model.Booking) request.getAttribute("booking");
        if (booking != null) {
    %>
        <div class="booking-details">
            <p><span class="label">Booking Number:</span> <%= booking.getBookingNumber() %></p>
            <p><span class="label">Customer Name:</span> <%= booking.getCustomerName() %></p>
            <p><span class="label">Customer Address:</span> <%= booking.getCustomerAddress() %></p>
            <p><span class="label">Telephone Number:</span> <%= booking.getTelephoneNumber() %></p>
            <p><span class="label">Destination:</span> <%= booking.getDestination() %></p>
            <p><span class="label">Booking Date:</span> <%= booking.getBookingDate() %></p>
        </div>
    <% } else { %>
        <p style="text-align:center; color:red;">No booking details found.</p>
    <% } %>
    <a class="back-link" href="index.jsp">Return to Home</a>
</div>
</body>
</html>
