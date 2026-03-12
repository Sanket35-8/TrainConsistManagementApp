package usecase5;

import java.util.LinkedList;
import java.util.Queue;

// Reservation class represents a booking request
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void displayRequest() {
        System.out.println("Guest: " + guestName + " | Requested Room: " + roomType);
    }
}

// BookingRequestQueue manages incoming requests in FIFO order
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Added booking request for " + reservation.getGuestName());
    }

    public Reservation processNextRequest() {
        return requestQueue.poll(); // Removes and returns the head of the queue
    }

    public boolean hasPendingRequests() {
        return !requestQueue.isEmpty();
    }

    public void displayPendingRequests() {
        System.out.println("=== Pending Booking Requests ===");
        for (Reservation r : requestQueue) {
            r.displayRequest();
        }
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Booking Request Queue (v5.1) ===");

        BookingRequestQueue queue = new BookingRequestQueue();

        // Simulate booking requests
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Suite Room"));

        // Display all pending requests
        queue.displayPendingRequests();

        System.out.println("\nProcessing requests in order (FIFO):");
        while (queue.hasPendingRequests()) {
            Reservation next = queue.processNextRequest();
            System.out.println("Processing request:");
            next.displayRequest();
        }

        System.out.println("=== End of Use Case 5 ===");
    }
}
