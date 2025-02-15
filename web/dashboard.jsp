<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset defaults */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #74ABE2, #5563DE);
            min-height: 100vh;
            display: flex;
            flex-direction: column;
        }

        .navbar {
            background-color: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 2rem;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }

        .navbar .brand {
            font-weight: bold;
            font-size: 1.2rem;
            color: #333;
            text-decoration: none;
        }

        .nav-links {
            display: flex;
            gap: 1rem;
        }

        .nav-links a {
            color: #5563DE;
            text-decoration: none;
            font-size: 1rem;
            transition: color 0.3s;
        }

        .nav-links a:hover {
            color: #333;
        }

        .container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 2rem;
        }

        .welcome-message {
            background-color: #fff;
            padding: 2rem;
            border-radius: 8px;
            max-width: 600px;
            width: 100%;
            text-align: center;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
        }

        .welcome-message h1 {
            margin-bottom: 1rem;
            color: #333;
        }

        .welcome-message p {
            margin-bottom: 1.5rem;
            color: #555;
            line-height: 1.6;
        }

        .buttons {
            display: flex;
            flex-wrap: wrap;
            gap: 1rem;
            justify-content: center;
        }

        .buttons a {
            text-decoration: none;
            color: #fff;
            background-color: #5563DE;
            padding: 0.75rem 1.5rem;
            border-radius: 4px;
            transition: background-color 0.3s;
        }

        .buttons a:hover {
            background-color: #444fb7;
        }

        footer {
            background-color: #fff;
            text-align: center;
            padding: 1rem;
        }

        footer p {
            color: #666;
            font-size: 0.9rem;
        }

        /* Responsive design */
        @media (max-width: 600px) {
            .navbar {
                flex-direction: column;
                align-items: flex-start;
            }

            .nav-links {
                margin-top: 1rem;
                flex-direction: column;
                align-items: flex-start;
            }
        }
    </style>
</head>
<body>
    <header class="navbar">
        <a href="#" class="brand">Mega City Cab</a>
    </header>

    <div class="container">
        <div class="welcome-message">
            <h1>Welcome to Mega City Cab Dashboard</h1>
            <p>
                Hello, 
                <% 
                    // Display the logged-in username from the session, if available
                    String loggedInUser = (String) session.getAttribute("username");
                    if (loggedInUser != null) {
                        out.print(loggedInUser);
                    } else {
                        out.print("Guest");
                    }
                %>!
                <br><br>
                This is your central hub for managing cab bookings, billing, and more. 
                Use the buttons below or the navigation bar above to access different features.
            </p>
            <div class="buttons">
                <a href="booking.jsp">New Booking</a>
                <a href="BookingServlet?action=list">View Bookings</a>
                <a href="billing.jsp">Billing</a>
                <a href="help.jsp">Help</a>
            </div>
        </div>
    </div>

    <footer>
        <p>© <%= java.time.Year.now() %> Mega City Cab. All rights reserved.</p>
    </footer>
</body>
</html>
