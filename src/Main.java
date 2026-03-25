import java.util.*;

// Custom Exception
class CancellationException extends Exception {
    public CancellationException(String message) {
        super(message);
    }
}

// Reservation Model
class Reservation {
    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;
    private boolean isActive;

    public Reservation(String reservationId, String guestName, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.isActive = true;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void cancel() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                ", Guest: " + guestName +
                ", Room Type: " + roomType +
                ", Room ID: " + roomId +
                ", Status: " + (isActive ? "ACTIVE" : "CANCELLED");
    }
}

// Inventory Management
class RoomInventory {
    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Standard", 2);
        availability.put("Deluxe", 1);
        availability.put("Suite", 1);
    }

    public void increment(String roomType) {
        availability.put(roomType, availability.getOrDefault(roomType, 0) + 1);
    }

    public void display() {
        System.out.println("Inventory: " + availability);
    }
}

// Booking History
class BookingHistory {
    private Map<String, Reservation> reservations = new HashMap<>();

    public void addReservation(Reservation res) {
        reservations.put(res.getReservationId(), res);
    }

    public Reservation getReservation(String id) {
        return reservations.get(id);
    }

    public void displayAll() {
        for (Reservation r : reservations.values()) {
            System.out.println(r);
        }
    }
}

// Cancellation Service (Core Logic)
class CancellationService {

    private BookingHistory history;
    private RoomInventory inventory;

    // Stack to track released room IDs (LIFO rollback)
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(BookingHistory history, RoomInventory inventory) {
        this.history = history;
        this.inventory = inventory;
    }

    public void cancelBooking(String reservationId) {
        try {
            Reservation res = history.getReservation(reservationId);

            // Validation
            if (res == null) {
                throw new CancellationException("Reservation does not exist.");
            }

            if (!res.isActive()) {
                throw new CancellationException("Reservation already cancelled.");
            }

            // Step 1: Record room ID in rollback stack
            rollbackStack.push(res.getRoomId());

            // Step 2: Restore inventory
            inventory.increment(res.getRoomType());

            // Step 3: Update reservation state
            res.cancel();

            System.out.println("Cancellation successful for Reservation ID: " + reservationId);

        } catch (CancellationException e) {
            System.out.println("Cancellation failed: " + e.getMessage());
        }
    }

    public void displayRollbackStack() {
        System.out.println("Rollback Stack (LIFO): " + rollbackStack);
    }
}

// Main Class
 class UseCase10BookingCancellation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        Reservation r1 = new Reservation("RES201", "Alice", "Deluxe", "D1");
        Reservation r2 = new Reservation("RES202", "Bob", "Suite", "S1");

        history.addReservation(r1);
        history.addReservation(r2);

        CancellationService service = new CancellationService(history, inventory);

        System.out.println("Initial State:");
        history.displayAll();
        inventory.display();

        System.out.println("\n--- Cancellation Attempts ---");

        // Valid cancellation
        service.cancelBooking("RES201");

        // Duplicate cancellation
        service.cancelBooking("RES201");

        // Invalid reservation
        service.cancelBooking("RES999");

        System.out.println("\n--- Final State ---");
        history.displayAll();
        inventory.display();

        System.out.println();
        service.displayRollbackStack();
    }
}