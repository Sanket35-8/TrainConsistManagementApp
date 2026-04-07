import java.util.HashMap;
import java.util.Map;

public class TrainConsistAppUC6 {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App (UC6) ===");

        // Create HashMap for Bogie -> Capacity
        Map<String, Integer> bogieCapacityMap = new HashMap<>();

        // Add bogies with capacities
        bogieCapacityMap.put("Sleeper", 72);
        bogieCapacityMap.put("AC Chair", 54);
        bogieCapacityMap.put("First Class", 24);

        // Display bogie capacities
        System.out.println("\nBogie Capacity Details:");

        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println("Bogie: " + entry.getKey() +
                    " | Capacity: " + entry.getValue());
        }
    }
}
