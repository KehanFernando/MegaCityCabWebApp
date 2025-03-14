<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Mega City Cab</title>
    <link rel="icon" type="image/png" href="img/MCC.png">
    <style>
        /* Global Reset */
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            padding: 20px;
        }
        .container {
            background: #fff;
            padding: 2rem;
            border-radius: 8px;
            width: 600px;
            max-width: 100%;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            margin-top: 90px;
        }
        h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }
        .search-section {
            text-align: center;
            margin-bottom: 2rem;
        }
        .search-section input[type="text"] {
            width: 75%;
            padding: 0.75rem;
            font-size: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        .search-section button {
            padding: 0.75rem 1.5rem;
            background: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            margin-left: 10px;
            cursor: pointer;
            transition: background 0.3s ease;
        }
        .search-section button:hover {
            background: #444fb7;
        }
        .form-section {
            margin-top: 20px;
        }
        .form-section label {
            font-weight: bold;
            margin-bottom: 0.5rem;
            display: block;
            color: #555;
        }
        .form-section input[type="text"],
        .form-section input[type="number"] {
            width: 100%;
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 1rem;
        }
        .form-section input[readonly] {
            background: #e9e9e9;
        }
        .form-section button {
            width: 100%;
            padding: 0.75rem;
            background: #5563DE;
            color: #fff;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            transition: background 0.3s ease;
            margin-bottom: 10px;
        }
        .form-section button:hover {
            background: #444fb7;
        }
        .message, .errorMessage {
            text-align: center;
            margin-bottom: 1rem;
            font-size: 1rem;
        }
        .message { color: green; }
        .errorMessage { color: red; }
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
        /* Style for the Dashboard link with icon */
        header.navbar .nav-links a[href="dashboard.jsp"] {
          position: relative;
          padding-left: 30px;
          background: url('https://img.icons8.com/?size=100&id=S5D5w5vFLhYp&format=png&color=000000') no-repeat left center;
          background-size: 20px 20px;
        }
        header.navbar .nav-links a[href="dashboard.jsp"]:hover::after {
          content: 'Dashboard';
          position: absolute;
          bottom: -30px;
          left: 50%;
          transform: translateX(-50%);
          background: #333;
          color: #fff;
          padding: 4px 8px;
          border-radius: 4px;
          font-size: 0.85rem;
          white-space: nowrap;
          opacity: 0;
          transition: opacity 0.3s;
          pointer-events: none;
        }
        header.navbar .nav-links a[href="dashboard.jsp"]:hover::after {
          opacity: 1;
        }
        /* Style for the Dashboard link with icon */
        header.navbar .nav-links a[href="index.jsp"] {
          position: relative;
          padding-left: 30px;
          background: url('https://img.icons8.com/?size=100&id=111473&format=png&color=000000') no-repeat left center;
          background-size: 20px 20px;
        }
        header.navbar .nav-links a[href="index.jsp"]:hover::after {
          content: 'Dashboard';
          position: absolute;
          bottom: -30px;
          left: 50%;
          transform: translateX(-50%);
          background: #333;
          color: #fff;
          padding: 4px 8px;
          border-radius: 4px;
          font-size: 0.85rem;
          white-space: nowrap;
          opacity: 0;
          transition: opacity 0.3s;
          pointer-events: none;
        }
        header.navbar .nav-links a[href="index.jsp"]:hover::after {
          opacity: 1;
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
            <a href="viewEdt.jsp">View</a>
            <a href="billing.jsp">Billing</a>
            <a href="help.jsp">Help</a>
            <a href="dashboard.jsp"></a>
            <a href="index.jsp"></a>
        </nav>
    </header>
    
<div class="container">
    <h2>Search Record</h2>
    <!-- Search Section -->
    <div class="search-section">
        <form action="ViewEditServlet" method="get">
            <input type="text" name="searchId" placeholder="Enter ID (e.g., CUS_123, DI456, V789 or NIC)" required />
            <button type="submit">Search</button>
        </form>
    </div>

    <!-- Display messages if available -->
    <% if(request.getAttribute("message") != null) { %>
        <div class="message"><%= request.getAttribute("message") %></div>
    <% } %>
    <% if(request.getAttribute("errorMessage") != null) { %>
        <div class="errorMessage"><%= request.getAttribute("errorMessage") %></div>
    <% } %>

    <!-- Conditionally display edit form based on search result -->
    <%
        String recordType = (String) request.getAttribute("recordType");
        if(recordType != null) {
            if(recordType.equals("customer")) {
                com.megacitycab.model.Customer customer = (com.megacitycab.model.Customer) request.getAttribute("customer");
                if(customer != null) {
    %>
                    <h2>View/Edit Customer Details</h2>
                    <div class="form-section">
                        <!-- Update Customer Form -->
                        <form action="ViewEditServlet" method="post">
                            <label>Registration Number</label>
                            <input type="text" name="registrationNumber" value="<%= customer.getRegistrationNumber() %>" readonly />
                            
                            <label>Customer Name</label>
                            <input type="text" name="customerName" value="<%= customer.getName() %>" required />
                            
                            <label>Customer Address</label>
                            <input type="text" name="customerAddress" value="<%= customer.getAddress() %>" required />
                            
                            <label>NIC Number</label>
                            <input type="text" name="customerNic" value="<%= customer.getNic() %>" required />
                            
                            <label>Contact Number</label>
                            <input type="text" name="customerTelephone" value="<%= customer.getTelephone() %>" required />
                            
                            <button type="submit">Update Customer Details</button>
                        </form>
                        <!-- Delete Customer Form -->
                        <form action="ViewEditServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this customer account?');">
                            <input type="hidden" name="registrationNumber" value="<%= customer.getRegistrationNumber() %>" />
                            <input type="hidden" name="deleteCustomer" value="true" />
                            <button type="submit">Delete Customer Account</button>
                        </form>
                    </div>
    <%
                } else {
    %>
                    <div class="errorMessage">Customer record not found.</div>
    <%
                }
            } else if(recordType.equals("driver")) {
                com.megacitycab.model.Driver driver = (com.megacitycab.model.Driver) request.getAttribute("driver");
                if(driver != null) {
    %>
                    <h2>View/Edit Driver Details</h2>
                    <div class="form-section">
                        <!-- Update Driver Form -->
                        <form action="ViewEditServlet" method="post">
                            <label>Driver ID</label>
                            <input type="text" name="driverId" value="<%= driver.getDriverId() %>" readonly />
                            
                            <label>Driver Name</label>
                            <input type="text" name="driverName" value="<%= driver.getName() %>" required />
                            
                            <label>Driver License Number</label>
                            <input type="text" name="licenseNumber" value="<%= driver.getLicenseNumber() %>" required />
                            
                            <label>Driver Contact Number</label>
                            <input type="text" name="phone" value="<%= driver.getPhone() %>" required />
                            
                            <label>Driver Address</label>
                            <input type="text" name="driverAddress" value="<%= driver.getAddress() %>" required />
                            
                            <label>Assigned Car ID</label>
                            <input type="text" name="assignedCarId" value="<%= driver.getAssignedCarId() %>" required />
                            
                            <button type="submit">Update Driver Details</button>
                        </form>
                        <!-- Delete Driver Form -->
                        <form action="ViewEditServlet" method="post" onsubmit="return confirm('Are you sure you want to delete this driver account?');">
                            <input type="hidden" name="driverId" value="<%= driver.getDriverId() %>" />
                            <input type="hidden" name="deleteDriver" value="true" />
                            <button type="submit">Delete Driver Account</button>
                        </form>
                    </div>
    <%
                } else {
    %>
                    <div class="errorMessage">Driver record not found.</div>
    <%
                }
            } else if(recordType.equals("vehicle")) {
                com.megacitycab.model.Car car = (com.megacitycab.model.Car) request.getAttribute("car");
                if(car != null) {
    %>
                    <h2>View/Edit Vehicle Details</h2>
                    <div class="form-section">
                        <!-- Update Vehicle Form -->
                        <form action="ViewEditServlet" method="post">
                            <label>Vehicle Registration ID</label>
                            <input type="text" name="vehicleRegId" value="<%= car.getVehicleRegId() %>" readonly />
                            
                            <label>Vehicle Type</label>
                            <input type="text" name="vehicleType" value="<%= car.getVehicleType() %>" required />
                            
                            <label>License Plate</label>
                            <input type="text" name="licensePlate" value="<%= car.getLicensePlate() %>" required />
                            
                            <label>Vehicle Brand</label>
                            <input type="text" name="brand" value="<%= car.getBrand() %>" required />
                            
                            <label>Vehicle Model</label>
                            <input type="text" name="model" value="<%= car.getModel() %>" required />
                            
                            <label>Vehicle Color</label>
                            <input type="text" name="color" value="<%= car.getColor() %>" required />
                            
                            <label>Vehicle Seating Capacity</label>
                            <input type="number" name="seatingCapacity" value="<%= car.getSeatingCapacity() %>" required />
                            
                            <!-- Display and update the driverId for this vehicle -->
                            <label>Assigned Driver ID</label>
                            <input type="text" name="vehicleDriverId" value="<%= car.getDriverId() %>" required />
                            
                            <button type="submit">Update Vehicle Details</button>
                        </form>
                        <!-- Delete Vehicle Form -->
                        <form action="ViewEditServlet" method="post" onsubmit="return confirm('Are you sure you want to remove this vehicle?');">
                            <input type="hidden" name="vehicleRegId" value="<%= car.getVehicleRegId() %>" />
                            <input type="hidden" name="deleteVehicle" value="true" />
                            <button type="submit">Remove Vehicle</button>
                        </form>
                    </div>
    <%
                } else {
    %>
                    <div class="errorMessage">Vehicle record not found.</div>
    <%
                }
            }
        }
    %>
</div>
</body>
</html>
