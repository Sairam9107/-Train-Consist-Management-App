import java.io.*;
import java.util.*;

// Reservation Model (Serializable)
class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return reservationId + " | " + guestName + " | " + roomType;
    }
}

// Inventory Model (Serializable)
class RoomInventory implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Standard", 2);
        availability.put("Deluxe", 1);
        availability.put("Suite", 1);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }

    public void display() {
        System.out.println("Inventory: " + availability);
    }
}

// Wrapper Class for Full System State
class SystemState implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Reservation> reservations;
    private RoomInventory inventory;

    public SystemState(List<Reservation> reservations, RoomInventory inventory) {
        this.reservations = reservations;
        this.inventory = inventory;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public RoomInventory getInventory() {
        return inventory;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "system_state.ser";

    // Save state to file
    public void save(SystemState state) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(state);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving system state: " + e.getMessage());
        }
    }

    // Load state from file
    public SystemState load() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No saved state found. Starting fresh.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            SystemState state = (SystemState) ois.readObject();
            System.out.println("System state loaded successfully.");
            return state;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading state. Starting with default data.");
            return null;
        }
    }
}

// Main Class
 class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        PersistenceService persistenceService = new PersistenceService();

        // Step 1: Try loading previous state
        SystemState state = persistenceService.load();

        List<Reservation> reservations;
        RoomInventory inventory;

        if (state == null) {
            // Fresh start
            reservations = new ArrayList<>();
            inventory = new RoomInventory();

            System.out.println("\n--- Fresh System Initialized ---");

            // Simulate bookings
            reservations.add(new Reservation("RES301", "Alice", "Deluxe"));
            reservations.add(new Reservation("RES302", "Bob", "Standard"));

        } else {
            // Restore previous state
            reservations = state.getReservations();
            inventory = state.getInventory();

            System.out.println("\n--- Restored System State ---");
        }

        // Display current state
        System.out.println("\nReservations:");
        for (Reservation r : reservations) {
            System.out.println(r);
        }

        inventory.display();

        // Step 2: Save state before shutdown
        SystemState newState = new SystemState(reservations, inventory);
        persistenceService.save(newState);

        System.out.println("\nSystem ready for shutdown and recovery.");
    }
}