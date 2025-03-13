package VehicleTest;

import com.megacitycab.model.Car;
import com.megacitycab.service.CarRegistrationService;

public class VehicleTest {
    public static void main(String[] args) {
        CarRegistrationService service = new CarRegistrationService();
        
        System.out.println("Test Case 1: Register a New Vehicle");
        boolean isRegistered = service.registerVehicle("Car", "C1234", "ABC-1234", "Civic", "Honda", "Red", 5);
        if (isRegistered) {
            System.out.println("✅ Vehicle Registration Successful - Passed");
        } else {
            System.out.println("❌ Vehicle Registration Failed - Failed");
        }
        
        System.out.println("\nTest Case 2: Retrieve Available Vehicle of Type Car");
        Car availableCar = service.getAvailableVehicle("Car");
        if (availableCar != null) {
            System.out.println("ℹ️ Retrieved Vehicle Details: " + availableCar);
            System.out.println("✅ Available Vehicle Found - Passed");
        } else {
            System.out.println("❌ No Available Vehicle Found - Failed");
        }
        
        System.out.println("\nTest Case 3: Retrieve Available Not Booked Vehicle of Type Car");
        Car notBookedCar = service.getAvailableNotBookedVehicle("Car");
        if (notBookedCar != null) {
            System.out.println("ℹ️ Retrieved Available Not Booked Vehicle: " + notBookedCar);
            System.out.println("✅ Available Not Booked Vehicle Found - Passed");
        } else {
            System.out.println("❌ No Available Not Booked Vehicle Found - Failed");
        }
        
        System.out.println("\nTest Case 4: Register a Duplicate Vehicle");
        boolean isDuplicateRegistered = service.registerVehicle("Car", "C1234", "ABC-1234", "Civic", "Honda", "Red", 5);
        if (!isDuplicateRegistered) {
            System.out.println("✅ Duplicate Vehicle Registration Prevented - Passed");
        } else {
            System.out.println("❌ Duplicate Vehicle Registered - Failed");
        }
        
        System.out.println("\nFinal Validation: Ensure Deleted Vehicle is Not Retrieved");
        Car deletedVehicle = service.getAvailableVehicle("Car");
        if (deletedVehicle == null) {
            System.out.println("✅ Deleted Vehicle Not Found - Passed");
        } else {
            System.out.println("❌ Deleted Vehicle Still Found - Failed");
        }
    }
}

