<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" type="image/png" href="img/MCC.png">
    <style>
        /* Reset defaults */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        /* Body with a subtle gradient background */
        body {
            font-family: Arial, sans-serif;
            background: linear-gradient(135deg, #f1f3f6, #ffffff);
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }

        /* Login container styling */
        .login-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 320px;
            text-align: center;
            position: relative;
            margin-top: 20px; /* To avoid overlapping with the fixed header */
            transition: transform 0.3s, box-shadow 0.3s;
        }
        /* Hover effect for container */
        .login-container:hover {
            transform: translateY(-4px);
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.15);
        }

        .login-container h2 {
            
            color: #333;
        }
        .welcome-message {
            margin-bottom: 1rem;
            font-size: 1.1rem;
            color: #555;
        }

        /* Image styling */
        .welcome-image {
            width: 100%;
            border-radius: 8px;
        }

        /* Form and input styling */
        .login-container form {
            display: flex;
            flex-direction: column;
        }
        .login-container label {
            margin-bottom: 0.5rem;
            color: #555;
            font-weight: bold;
            text-align: left;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
            transition: border-color 0.3s, box-shadow 0.3s;
        }
        .login-container input[type="text"]:focus,
        .login-container input[type="password"]:focus {
            border-color: #5563DE;
            box-shadow: 0 0 0 2px rgba(85, 99, 222, 0.2);
            outline: none;
        }

        /* Button styling with hover and focus */
        .login-container button {
            padding: 0.75rem;
            background-color: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s, box-shadow 0.3s;
        }
        .login-container button:hover {
            background-color: #444fb7;
            box-shadow: 0 4px 12px rgba(68, 79, 183, 0.3);
        }
        .login-container button:focus {
            outline: none;
            box-shadow: 0 0 0 3px rgba(85, 99, 222, 0.3);
        }

        /* Error message styling */
        .error-message {
            color: red;
            margin-bottom: 1rem;
            text-align: center;
        }
    </style>
</head>
<body>
    
    <div class="login-container">
        <h2>Login</h2>
        <!-- Welcome Image -->
        <img src="img/wlmc2.jpg" alt="Welcome" class="welcome-image">
        
        <!-- Dynamic Greeting -->
        <%
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            String greeting;
            if (hour < 12) {
                greeting = "Good Morning!";
            } else if (hour < 18) {
                greeting = "Good Afternoon!";
            } else {
                greeting = "Good Evening!";
            }
        %>
        <div class="welcome-message"><%= greeting %> <br><br> Welcome to Mega City Cab.</div>
        
        
        <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
        <% if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>
        <form action="LoginServlet" method="post">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username" required>
            
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
            
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
