package usecase12;

import java.io.*;
import java.util.*;

class Inventory implements Serializable {
    private static final long serialVersionUID = 1L;
    Map<String, Integer> roomAvailability = new HashMap<>();

    public void addRoomType(String type, int count) {
        roomAvailability.put(type, count);
    }

    public boolean allocateRoom(String type) {
        int available = roomAvailability.getOrDefault(type, 0);
        if (available > 0) {
            roomAvailability.put(type, available - 1);
            return true;
        }
        return false;
    }

    public void displayInventory() {
        System.out.println("=== Current Inventory ===");
        roomAvailability.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}

class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    String reservationId;
    String guestName;
    String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

public class UseCase12DataPersistenceRecovery {

    private static final String FILE_NAME = "bookingData.ser";

    public static void main(String[] args) {
        System.out.println("=== Data Persistence & System Recovery (v12.0) ===");

        Inventory inventory;
        List<Reservation> bookings;

        // Load persisted data if exists
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                inventory = (Inventory) ois.readObject();
                bookings = (List<Reservation>) ois.readObject();
                System.out.println("System state restored from file.");
            } catch (Exception e) {
                System.out.println("Failed to restore data. Initializing fresh state.");
                inventory = new Inventory();
                bookings = new ArrayList<>();
            }
        } else {
            inventory = new Inventory();
            bookings = new ArrayList<>();
        }

        // Sample data
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        bookings.add(new Reservation("R101", "Alice", "Single"));
        bookings.add(new Reservation("R102", "Bob", "Double"));

        // Display
        inventory.displayInventory();
        System.out.println("--- Bookings ---");
        for (Reservation r : bookings) {
            System.out.println(r.reservationId + " | " + r.guestName + " | " + r.roomType);
        }

        // Persist data
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(inventory);
            oos.writeObject(bookings);
            System.out.println("System state saved to file.");
        } catch (IOException e) {
            System.out.println("Failed to save system state: " + e.getMessage());
        }

        System.out.println("=== End of Use Case 12 ===");
    }
}
