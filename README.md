# Mega City Cab Web Application

![Mega City Cab Logo](img/MCC.png)

Mega City Cab is a comprehensive web-based cab management system for Colombo City. It streamlines customer bookings, driver and vehicle registration, billing, and record management. The system is built using Java (JSP/Servlets) and follows the MVC pattern with a well-organized package structure. The application is designed with user-friendly interfaces, clear error handling, and modular services using common design patterns such as Singleton, DAO, and Builder.

## Features

- **User Authentication:** Secure login using username and password.
- **Customer Booking:** New bookings are registered with an auto-generated booking number. It supports auto-filling customer details (name, contact) via Customer Registration Number or NIC.
- **Vehicle Assignment:** Automatically fetches available vehicles (ensuring not already booked) and auto-populates vehicle details.
- **Billing:** Calculate the total fare based on base fare, per-mile cost, and applicable taxes. Provides a breakdown and a print option.
- **Driver & Vehicle Management:** Manage registrations and edit/delete records using dedicated forms.
- **Help & Guidelines:** An integrated help section provides usage instructions.
- **Dashboard:** A central hub to navigate between functions.

## Technologies Used

- **Java EE:** Servlets, JSP
- **Design Patterns:** MVC, Singleton, DAO, Builder
- **Database:** MySQL (MySQL Workbench 8.0 CE recommended)
- **IDE:** NetBeans IDE 8.2
- **Build Tool:** Ant (build.xml included)
- **Version Control:** Git

## Project Structure

```
MegaCityCabWebApp/ (Project Root)
├── nbproject/             # NetBeans project configuration files
├── build.xml              # Ant build file for build management
├── src/
│   └── conf/
│       └── java/
│           └── com/
│               └── megacitycab/
│                   ├── controller/        // Servlet controllers
│                   ├── model/             // Domain objects (POJOs)
│                   ├── service/           // Business logic classes
│                   └── dao/               // Data Access Objects (DAO)
└── web/
    ├── index.jsp          # Home/Login page
    ├── booking.jsp        # Booking entry form
    ├── displayBookings.jsp# Display booking details
    ├── billing.jsp        # Billing and bill printing page
    ├── help.jsp           # Help & guidelines page
    ├── carReg.jsp         # Vehicle registration page
    ├── customerReg.jsp    # Customer registration page
    ├── dashboard.jsp      # Dashboard after login
    ├── driverReg.jsp      # Driver registration page
    ├── register.jsp       # Registration landing page
    ├── viewEdt.jsp        # View, edit, and delete records
    └── WEB-INF/
        ├── web.xml        # Servlet mapping and configuration
        └── META_INF/
```

## Web Application Screenshots

Below are some screenshots of the **Mega City Cab Web Application**:

### Login Page
![Login Page](screenshots/login.png)

### Dashboard
![Dashboard](screenshots/dashboard.png)

### Booking Page
![Booking Page](screenshots/booking.png)

### Vehicle Registration Page
![Vehicle Registration](screenshots/vehicle_registration.png)

### Billing Page
![Billing Page](screenshots/billing.png)

### Customer Management
![Customer Management](screenshots/customer_management.png)

## Installation

### Prerequisites

- **Java Development Kit (JDK):**  
  Download and install Java from [Oracle JDK Download](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.java.net/install/).

- **NetBeans IDE 8.2:**  
  Download from [NetBeans IDE 8.2 Downloads](https://netbeans.apache.org/download/nb82/nb82.html).

- **MySQL Workbench 8.0 CE:**  
  Download from [MySQL Community Downloads](https://dev.mysql.com/downloads/workbench/).

### Database Setup

1. **Create the Database:**
   - Open MySQL Workbench.
   - Create a new database named `megacitycab`.
   - Execute the SQL scripts (create tables such as `customers`, `bookings`, `vehicles`, `drivers`, and `users`) according to your project schema. Make sure to include necessary fields (e.g., registration numbers, booking details).

2. **Configure DB Connection:**
   - The database connection is managed by `DBConnectionManager.java`.
   - Update the following parameters in `DBConnectionManager.java` if needed:
     ```java
     private static final String DB_URL = "jdbc:mysql://localhost:3306/megacitycab?useSSL=false&serverTimezone=UTC";
     private static final String DB_USER = "root";  // Change if different
     private static final String DB_PASSWORD = "admin";  // Change if different
     ```

### Importing the Project into NetBeans

1. **Open NetBeans IDE 8.2.**
2. **Import the Project:**
   - Go to **File > Open Project**.
   - Navigate to the project root folder (`MegaCityCabWebApp/`) and select it.
3. **Build & Run:**
   - Use the Ant build file (`build.xml`) to build the project.
   - Deploy the application to your preferred Java EE application server (e.g., GlassFish or Apache Tomcat).

## Running the Application

1. **Start the Database Server:**
   - Ensure MySQL server is running.
2. **Deploy the Web Application:**
   - Build the project using NetBeans.
   - Run the project; NetBeans will deploy the application and open it in your default browser.
3. **Login:**
   - Navigate to the home page (e.g., `index.jsp`) and log in using a valid username and password stored in your `users` table.
4. **Navigation:**
   - Once logged in, use the dashboard to access features like Booking, Registration, Billing, etc.

## Version Control

This project is maintained using Git. Below are some example Git commands:

```bash
# Clone the repository
git clone https://github.com/yourusername/MegaCityCabWebApp.git

# Change directory to the project folder
cd MegaCityCabWebApp

# Create a new branch for feature development
git checkout -b feature/new-feature

# Stage and commit changes
git add .
git commit -m "Implement new feature"

# Push changes to remote repository
git push origin feature/new-feature
```

## Contributing

Contributions are welcome! Please create a pull request or submit issues for any bugs or feature requests. When contributing, ensure your code adheres to the current project structure and coding standards.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Direct Download Links

- **Java SE JDK:** [Oracle JDK Download](https://www.oracle.com/java/technologies/javase-downloads.html)
- **NetBeans IDE 8.2:** [NetBeans IDE 8.2 Downloads](https://netbeans.apache.org/download/nb82/nb82.html)
- **MySQL Workbench 8.0 CE:** [MySQL Workbench Download](https://dev.mysql.com/downloads/workbench/)
