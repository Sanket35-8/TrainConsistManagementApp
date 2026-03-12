package usecase11;

import java.util.*;
import java.util.concurrent.*;

class Inventory {
    private final Map<String, Integer> roomAvailability = new HashMap<>();

    public synchronized void addRoomType(String type, int count) {
        roomAvailability.put(type, count);
    }

    public synchronized boolean allocateRoom(String type) {
        int available = roomAvailability.getOrDefault(type, 0);
        if (available > 0) {
            roomAvailability.put(type, available - 1);
            return true;
        }
        return false;
    }

    public synchronized void displayInventory() {
        System.out.println("=== Current Inventory ===");
        roomAvailability.forEach((type, count) -> System.out.println(type + ": " + count));
    }
}

class BookingRequest {
    String reservationId;
    String guestName;
    String roomType;

    BookingRequest(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingProcessor implements Runnable {
    private final BlockingQueue<BookingRequest> queue;
    private final Inventory inventory;

    public BookingProcessor(BlockingQueue<BookingRequest> queue, Inventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        try {
            while (!queue.isEmpty()) {
                BookingRequest req = queue.take();
                boolean success = inventory.allocateRoom(req.roomType);
                System.out.println(Thread.currentThread().getName() + " processed "
                        + req.guestName + "'s request for " + req.roomType + ": "
                        + (success ? "SUCCESS" : "FAILED"));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Concurrent Booking Simulation (v11.0) ===");

        Inventory inventory = new Inventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        inventory.addRoomType("Suite", 2);

        BlockingQueue<BookingRequest> bookingQueue = new LinkedBlockingQueue<>();
        bookingQueue.add(new BookingRequest("R101", "Alice", "Single"));
        bookingQueue.add(new BookingRequest("R102", "Bob", "Double"));
        bookingQueue.add(new BookingRequest("R103", "Charlie", "Suite"));
        bookingQueue.add(new BookingRequest("R104", "David", "Single"));
        bookingQueue.add(new BookingRequest("R105", "Eve", "Double"));

        Thread t1 = new Thread(new BookingProcessor(bookingQueue, inventory), "Processor-1");
        Thread t2 = new Thread(new BookingProcessor(bookingQueue, inventory), "Processor-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        inventory.displayInventory();
        System.out.println("=== End of Use Case 11 ===");
    }
}