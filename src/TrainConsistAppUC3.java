import java.util.HashSet;
import java.util.Set;

public class TrainConsistAppUC3 {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App (UC3) ===");

        // Create HashSet for Bogie IDs
        Set<String> bogieIds = new HashSet<>();

        // Add Bogie IDs (including duplicates)
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101"); // duplicate
        bogieIds.add("BG102"); // duplicate

        // Display unique bogie IDs
        System.out.println("\nUnique Bogie IDs:");
        System.out.println(bogieIds);
    }
}
