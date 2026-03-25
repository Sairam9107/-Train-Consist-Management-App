import java.util.*;

// Represents a Reservation (Confirmed Booking)
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private int numberOfNights;
    private double pricePerNight;

    public Reservation(String reservationId, String guestName, String roomType,
                       int numberOfNights, double pricePerNight) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.numberOfNights = numberOfNights;
        this.pricePerNight = pricePerNight;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNumberOfNights() {
        return numberOfNights;
    }

    public double getTotalCost() {
        return numberOfNights * pricePerNight;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room: " + roomType +
                ", Nights: " + numberOfNights +
                ", Total Cost: ₹" + getTotalCost();
    }
}

// Maintains Booking History (Ordered Storage)
class BookingHistory {
    private List<Reservation> confirmedBookings;

    public BookingHistory() {
        confirmedBookings = new ArrayList<>();
    }

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        confirmedBookings.add(reservation);
    }

    // Retrieve all bookings
    public List<Reservation> getAllReservations() {
        return Collections.unmodifiableList(confirmedBookings);
    }
}

// Generates Reports from Booking History
class BookingReportService {

    // Display all bookings
    public void displayAllBookings(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("=== Booking History ===");
        for (Reservation res : reservations) {
            System.out.println(res);
        }
    }

    // Generate summary report
    public void generateSummaryReport(List<Reservation> reservations) {
        int totalBookings = reservations.size();
        double totalRevenue = 0.0;

        for (Reservation res : reservations) {
            totalRevenue += res.getTotalCost();
        }

        System.out.println("\n=== Booking Summary Report ===");
        System.out.println("Total Bookings: " + totalBookings);
        System.out.println("Total Revenue: ₹" + totalRevenue);
    }
}

// Main Class
 class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulating confirmed bookings
        Reservation r1 = new Reservation("RES101", "Alice", "Deluxe", 2, 3000);
        Reservation r2 = new Reservation("RES102", "Bob", "Standard", 3, 2000);
        Reservation r3 = new Reservation("RES103", "Charlie", "Suite", 1, 5000);

        // Add to booking history (in order of confirmation)
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Admin views booking history
        List<Reservation> bookings = history.getAllReservations();
        reportService.displayAllBookings(bookings);

        // Admin generates report
        reportService.generateSummaryReport(bookings);

        System.out.println("\nNote: Booking history remains unchanged after reporting.");
    }
}