<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Help</title>
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
            background: linear-gradient(135deg, #74ABE2, #5563DE);
            color: #333;
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
        .help-container {
            background: #fff;
            max-width: 800px;
            margin: auto;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            margin-top: 60px;
        }
        .help-container h1 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #5563DE;
        }
        .help-section {
            margin-bottom: 1.5rem;
        }
        .help-section h2 {
            margin-bottom: 0.75rem;
            font-size: 1.25rem;
            color: #444;
        }
        .help-section p {
            line-height: 1.6;
            font-size: 1rem;
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
            <a href="BookingServlet?action=list">View Bookings</a>
            <a href="billing.jsp">Billing</a>
            <a href="help.jsp">Help</a>
        </nav>
    </header>
    
    <div class="help-container">
        <h1>System Help & Guidelines</h1>

        <div class="help-section">
            <h2>Login</h2>
            <p>
                Use your registered username and password to log in to the Mega City Cab system.
                If you encounter login issues, ensure that your credentials are correct or contact support.
            </p>
        </div>

        <div class="help-section">
            <h2>Booking a Cab</h2>
            <p>
                To book a cab, navigate to the booking page and fill in the required details such as your name, address,
                telephone number, and destination. Upon submission, your booking will be recorded and a unique booking number
                will be generated.
            </p>
        </div>

        <div class="help-section">
            <h2>View Booking Details</h2>
            <p>
                You can view your booking details by entering your booking number on the booking details page.
                This feature provides a summary of your booking, including the date, destination, and contact details.
            </p>
        </div>

        <div class="help-section">
            <h2>Billing</h2>
            <p>
                The billing page calculates your total fare based on your booking number. The fare includes a base charge,
                applicable taxes, and discounts (if any). Contact customer support for further billing inquiries.
            </p>
        </div>

        <div class="help-section">
            <h2>Driver & Car Information</h2>
            <p>
                The system maintains records of drivers and vehicles. This information is used to manage and assign
                drivers to customer bookings efficiently.
            </p>
        </div>

        <a class="back-link" href="dashboard.jsp">Return to Dashboard</a>
    </div>
</body>
</html>
