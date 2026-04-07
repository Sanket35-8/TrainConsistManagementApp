import java.util.ArrayList;
import java.util.List;

public class TrainConsistAppUC2 {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App (UC2) ===");

        // Create ArrayList for Passenger Bogies
        List<String> passengerBogies = new ArrayList<>();

        // Add Bogies
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        // Display Bogies
        System.out.println("\nPassenger Bogies after addition:");
        System.out.println(passengerBogies);

        // Remove a Bogie (AC Chair)
        passengerBogies.remove("AC Chair");

        // Display after removal
        System.out.println("\nPassenger Bogies after removal of AC Chair:");
        System.out.println(passengerBogies);

        // Check existence of Sleeper
        boolean exists = passengerBogies.contains("Sleeper");

        System.out.println("\nDoes Sleeper bogie exist? " + exists);

        // Final state
        System.out.println("\nFinal Passenger Bogies List:");
        System.out.println(passengerBogies);
    }
}