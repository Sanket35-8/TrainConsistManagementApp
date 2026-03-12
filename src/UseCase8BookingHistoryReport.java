package usecase8;

import java.util.*;

// Represents a confirmed reservation
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getReservationId() { return reservationId; }
    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    public void displayReservation() {
        System.out.println("Reservation ID: " + reservationId +
                " | Guest: " + guestName +
                " | Room: " + roomType);
    }
}

// Maintains the history of confirmed bookings
class BookingHistory {
    private List<Reservation> confirmedBookings = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        confirmedBookings.add(reservation);
        System.out.println("Added reservation " + reservation.getReservationId() + " to history.");
    }

    public List<Reservation> getHistory() {
        return Collections.unmodifiableList(confirmedBookings);
    }
}

// Generates reports from booking history
class BookingReportService {
    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void displayReport() {
        System.out.println("\n=== Booking History Report ===");
        for (Reservation r : history.getHistory()) {
            r.displayReservation();
        }
        System.out.println("Total Bookings: " + history.getHistory().size());
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        System.out.println("=== Book My Stay - Booking History & Reporting (v8.1) ===");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService(history);

        // Simulate confirmed reservations
        history.addReservation(new Reservation("R101", "Alice", "Single Room"));
        history.addReservation(new Reservation("R102", "Bob", "Double Room"));
        history.addReservation(new Reservation("R103", "Charlie", "Suite Room"));

        // Generate booking history report
        reportService.displayReport();

        System.out.println("=== End of Use Case 8 ===");
    }
}
