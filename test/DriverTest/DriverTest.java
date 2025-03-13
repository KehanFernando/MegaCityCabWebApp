package DriverTest;

import com.megacitycab.model.Driver;
import com.megacitycab.service.DriverService;

public class DriverTest {
    public static void main(String[] args) {
        DriverService driverService = new DriverService();

        // **Test Case 1: Register a New Driver**
        System.out.println("Test Case 1: Register a New Driver");
        Driver newDriver = new Driver.Builder("DI1234")
                .name("John Doe")
                .licenseNumber("DL98765")
                .phone("0712345678")
                .address("123 Main Street, Colombo")
                .assignedCarId("C1234") // Assigning to vehicle C1234
                .build();

        boolean isRegistered = driverService.registerDriver(newDriver);
        if (isRegistered) {
            System.out.println("✅ Driver Registration Successful - Passed");
        } else {
            System.out.println("❌ Driver Registration Failed - Failed");
        }

        // **Test Case 2: Retrieve Registered Driver**
        System.out.println("\nTest Case 2: Retrieve Registered Driver");
        try {
            Driver retrievedDriver = driverService.getDriver("DI1234");
            if (retrievedDriver != null) {
                System.out.println("ℹ️ Retrieved Driver Details: " + retrievedDriver);
                System.out.println("✅ Driver Retrieval Successful - Passed");
            } else {
                System.out.println("❌ Driver Not Found - Failed");
            }
        } catch (Exception e) {
            System.out.println("❌ Exception Occurred - Failed");
            e.printStackTrace();
        }

        // **Test Case 3: Assign a Driver to an Existing Vehicle**
        System.out.println("\nTest Case 3: Assign a Driver to an Existing Vehicle");
        boolean isVehicleUpdated = driverService.assignDriverToVehicle("C1234", "DI1234");
        if (isVehicleUpdated) {
            System.out.println("✅ Driver Assigned to Vehicle Successfully - Passed");
        } else {
            System.out.println("❌ Driver Assignment to Vehicle Failed - Failed");
        }

        // **Test Case 4: Register Duplicate Driver ID**
        System.out.println("\nTest Case 4: Register Duplicate Driver ID");
        Driver duplicateDriver = new Driver.Builder("DI1234")
                .name("Jane Smith")
                .licenseNumber("DL12345")
                .phone("0756789012")
                .address("456 Park Avenue, Kandy")
                .assignedCarId("C5678") // Assigning to another vehicle
                .build();

        boolean isDuplicateRegistered = driverService.registerDriver(duplicateDriver);
        if (!isDuplicateRegistered) {
            System.out.println("✅ Duplicate Driver Registration Prevented - Passed");
        } else {
            System.out.println("❌ Duplicate Driver Registration Allowed - Failed");
        }

        // **Test Case 5: Delete Driver**
        System.out.println("\nTest Case 5: Delete Driver");
        boolean isDeleted = driverService.deleteDriver("DI1234");
        if (isDeleted) {
            System.out.println("✅ Driver Deletion Successful - Passed");
        } else {
            System.out.println("❌ Driver Deletion Failed - Failed");
        }

        // **Final Validation: Ensure Deleted Driver Cannot Be Retrieved**
        System.out.println("\nFinal Validation: Ensure Deleted Driver Cannot Be Retrieved");
        try {
            Driver deletedDriver = driverService.getDriver("DI1234");
            if (deletedDriver == null) {
                System.out.println("✅ Deleted Driver Not Found - Passed");
            } else {
                System.out.println("❌ Deleted Driver Still Exists - Failed");
            }
        } catch (Exception e) {
            System.out.println("❌ Exception Occurred - Failed");
            e.printStackTrace();
        }

        System.out.println("\nBUILD SUCCESSFUL");
    }
