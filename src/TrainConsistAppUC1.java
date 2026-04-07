import java.util.ArrayList;
import java.util.List;

public class TrainConsistAppUC1 {

    public static void main(String[] args) {

        // Welcome Message
        System.out.println("=== Train Consist Management App ===");

        // Initialize empty train consist
        List<String> trainConsist = new ArrayList<>();

        // Display initial state
        System.out.println("Train consist initialized.");
        System.out.println("Initial number of bogies: " + trainConsist.size());
    }
}