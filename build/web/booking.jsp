<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - New Booking</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset defaults */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f2f2f2;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
            padding: 20px;
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
        .booking-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            width: 400px;
            max-width: 100%;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        }
        .booking-container h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }
        .booking-container form {
            display: flex;
            flex-direction: column;
        }
        .booking-container label {
            font-weight: bold;
            margin-bottom: 0.5rem;
            color: #555;
        }
        .booking-container input[type="text"],
        .booking-container input[type="date"] {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }
        .booking-container button {
            padding: 0.75rem;
            background: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .booking-container button:hover {
            background: #444fb7;
        }
        .message {
            text-align: center;
            margin-bottom: 1rem;
            font-size: 1rem;
            color: green;
        }
        .error-message {
            text-align: center;
            margin-bottom: 1rem;
            font-size: 1rem;
            color: red;
        }
    </style>
</head>
<body>
    <!-- Navigation Header -->
    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
        <nav class="nav-links">
            <a href="booking.jsp">New Booking</a>
            <a href="BookingServlet?action=list">View Bookings</a>
            <a href="register.jsp">Registration</a>
            <a href="billing.jsp">Billing</a>
            <a href="help.jsp">Help</a>
            <a href="dashboard.jsp">Dashboard</a>
        </nav>
    </header>
<!-- Existing booking container -->
<div class="booking-container">
    <h2>New Booking</h2>
    <%-- Display messages if available --%>
    <% 
        String message = (String) request.getAttribute("message");
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (message != null) { 
    %>
        <div class="message"><%= message %></div>
    <% } else if (errorMessage != null) { %>
        <div class="error-message"><%= errorMessage %></div>
    <% } %>
    <form action="BookingServlet" method="post">
        <label for="bookingNumber">Booking Number</label>
        <input type="text" id="bookingNumber" name="bookingNumber" placeholder="Enter booking number" required>

        <label for="customerName">Customer Name</label>
        <input type="text" id="customerName" name="customerName" placeholder="Enter your name" required>

        <label for="customerAddress">Customer Address</label>
        <input type="text" id="customerAddress" name="customerAddress" placeholder="Enter your address" required>

        <label for="telephoneNumber">Telephone Number</label>
        <input type="text" id="telephoneNumber" name="telephoneNumber" placeholder="Enter telephone number" required>

        <label for="destination">Destination</label>
        <input type="text" id="destination" name="destination" placeholder="Enter destination" required>

        <label for="bookingDate">Booking Date</label>
        <input type="date" id="bookingDate" name="bookingDate">

        <button type="submit">Submit Booking</button>
    </form>
</div>
</body>
</html>
