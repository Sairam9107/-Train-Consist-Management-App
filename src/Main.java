import java.util.*;

// Custom Exception for Invalid Booking
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Represents Room Inventory
class RoomInventory {
    private Map<String, Integer> roomAvailability;

    public RoomInventory() {
        roomAvailability = new HashMap<>();

        // Initial inventory
        roomAvailability.put("Standard", 2);
        roomAvailability.put("Deluxe", 1);
        roomAvailability.put("Suite", 1);
    }

    // Validate room type
    public void validateRoomType(String roomType) throws InvalidBookingException {
        if (!roomAvailability.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
    }

    // Check availability
    public void validateAvailability(String roomType) throws InvalidBookingException {
        int available = roomAvailability.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }
    }

    // Allocate room safely
    public void allocateRoom(String roomType) throws InvalidBookingException {
        validateRoomType(roomType);
        validateAvailability(roomType);

        int current = roomAvailability.get(roomType);

        if (current - 1 < 0) {
            throw new InvalidBookingException("Inventory cannot be negative!");
        }

        roomAvailability.put(roomType, current - 1);
    }

    public void displayInventory() {
        System.out.println("Current Inventory: " + roomAvailability);
    }
}

// Booking Service with Validation
class BookingService {
    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void createBooking(String guestName, String roomType) {
        try {
            // Input validation
            if (guestName == null || guestName.trim().isEmpty()) {
                throw new InvalidBookingException("Guest name cannot be empty.");
            }

            // Validate and allocate
            inventory.allocateRoom(roomType);

            // If all validations pass
            System.out.println("Booking successful for " + guestName +
                    " in " + roomType + " room.");

        } catch (InvalidBookingException e) {
            // Graceful failure
            System.out.println("Booking failed: " + e.getMessage());
        }
    }
}

// Main Class
 class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService bookingService = new BookingService(inventory);

        // Initial state
        inventory.displayInventory();

        System.out.println("\n--- Booking Attempts ---");

        // Valid booking
        bookingService.createBooking("Alice", "Deluxe");

        // Invalid room type
        bookingService.createBooking("Bob", "Premium");

        // Empty guest name
        bookingService.createBooking("", "Standard");

        // Exhaust inventory
        bookingService.createBooking("Charlie", "Suite");
        bookingService.createBooking("David", "Suite"); // should fail

        // Final state
        System.out.println("\n--- Final Inventory ---");
        inventory.displayInventory();

        System.out.println("\nSystem remains stable after handling errors.");
    }
}