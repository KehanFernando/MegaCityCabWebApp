<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Billing Details</title>
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
            padding: 20px;
            min-height: 100vh;
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
        .billing-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            max-width: 500px;
            width: 100%;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .billing-container h1 {
            margin-bottom: 1rem;
            color: #333;
        }
        .billing-details {
            margin: 1.5rem 0;
            font-size: 1.1rem;
            color: #555;
            line-height: 1.6;
        }
        .billing-details span.label {
            font-weight: bold;
            color: #333;
        }
        .back-link {
            display: inline-block;
            margin-top: 1.5rem;
            text-decoration: none;
            font-size: 1rem;
            color: #5563DE;
            transition: color 0.3s ease;
        }
        .back-link:hover {
            color: #444;
        }
        .error-message {
            color: red;
            margin-bottom: 1rem;
            font-size: 1rem;
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
    
    <div class="billing-container">
        <h1>Billing Details</h1>
        <% 
            String bookingNumber = (String) request.getAttribute("bookingNumber");
            Double totalBill = (Double) request.getAttribute("totalBill");
            String errorMessage = (String) request.getAttribute("errorMessage");
            
            if (errorMessage != null) {
        %>
            <div class="error-message"><%= errorMessage %></div>
        <% } else if (bookingNumber != null && totalBill != null) { %>
            <div class="billing-details">
                <p><span class="label">Booking Number:</span> <%= bookingNumber %></p>
                <p><span class="label">Total Bill:</span> $<%= String.format("%.2f", totalBill) %></p>
            </div>
        <% } else { %>
            <div class="error-message">No billing information available.</div>
        <% } %>
        <a class="back-link" href="index.jsp">Return to Home</a>
    </div>
</body>
</html>
