package AdminLoginTest;

import com.megacitycab.dao.AdminDAO;
import com.megacitycab.model.Admin;

public class AdminLoginTest {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO(); // Create instance of DAO

        // Test Case 1: Valid Login
        System.out.println("Test Case 1: Valid Login");
        if (validateLogin("admin", "admin123", adminDAO)) {
            System.out.println("✅ Login Successful - Passed");
        } else {
            System.out.println("❌ Login Failed - Failed");
        }

        // Test Case 2: Invalid Password
        System.out.println("Test Case 2: Invalid Password");
        if (!validateLogin("admin", "wrongpass", adminDAO)) {
            System.out.println("✅ Invalid Credentials - Passed");
        } else {
            System.out.println("❌ Error Handling Failed - Failed");
        }

        // Test Case 3: Non-Existent Username
        System.out.println("Test Case 3: Non-Existent Username");
        if (!validateLogin("unknown", "admin123", adminDAO)) {
            System.out.println("✅ Non-Existent Username Handled - Passed");
        } else {
            System.out.println("❌ Error Handling Failed - Failed");
        }

        // Test Case 4: Empty Username and Password
        System.out.println("Test Case 4: Empty Username and Password");
        if (!validateLogin("", "", adminDAO)) {
            System.out.println("✅ Empty Credentials Handled - Passed");
        } else {
            System.out.println("❌ Error Handling Failed - Failed");
        }

        // Test Case 5: Null Username
        System.out.println("Test Case 5: Null Username");
        if (!validateLogin(null, "admin123", adminDAO)) {
            System.out.println("✅ Null Username Handled - Passed");
        } else {
            System.out.println("❌ Error Handling Failed - Failed");
        }

        // Test Case 6: Null Password
        System.out.println("Test Case 6: Null Password");
        if (!validateLogin("admin", null, adminDAO)) {
            System.out.println("✅ Null Password Handled - Passed");
        } else {
            System.out.println("❌ Error Handling Failed - Failed");
        }
    }

    // Function to Validate Login Using DAO
    public static boolean validateLogin(String username, String password, AdminDAO adminDAO) {
        if (username == null || password == null) {
            return false;
        }
        Admin admin = adminDAO.getAdminByUsername(username); // Fetch admin details from DB

        return admin != null && admin.getPassword().equals(password);
    }
}
