<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Mega City Cab - Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        /* Reset defaults */
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }
        body {
            font-family: Arial, sans-serif;
            background: #f2f2f2;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .login-container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 320px;
        }
        .login-container h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }
        .login-container form {
            display: flex;
            flex-direction: column;
        }
        .login-container label {
            margin-bottom: 0.5rem;
            color: #555;
            font-weight: bold;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }
        .login-container button {
            padding: 0.75rem;
            background-color: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .login-container button:hover {
            background-color: #444fb7;
        }
        .error-message {
            color: red;
            margin-bottom: 1rem;
            text-align: center;
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
    </style>
</head>
<body>
    <!-- Navigation Header -->
    <header class="navbar">
        <a class="brand" href="dashboard.jsp">Mega City Cab</a>
    </header>
    
    <div class="login-container">
        <h2>Login</h2>
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
