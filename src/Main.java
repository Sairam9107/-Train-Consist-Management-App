import java.util.*;

// Booking Request Model
class BookingRequest {
    private String guestName;
    private String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}

// Thread-safe Room Inventory
class RoomInventory {
    private Map<String, Integer> availability = new HashMap<>();

    public RoomInventory() {
        availability.put("Standard", 2);
        availability.put("Deluxe", 1);
        availability.put("Suite", 1);
    }

    // Critical Section: synchronized allocation
    public synchronized boolean allocateRoom(String roomType) {
        int available = availability.getOrDefault(roomType, 0);

        if (available > 0) {
            // Simulate processing delay (to expose race conditions if not synchronized)
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            availability.put(roomType, available - 1);
            return true;
        }

        return false;
    }

    public synchronized void displayInventory() {
        System.out.println("Final Inventory: " + availability);
    }
}

// Shared Booking Queue
class BookingQueue {
    private Queue<BookingRequest> queue = new LinkedList<>();

    public synchronized void addRequest(BookingRequest request) {
        queue.add(request);
    }

    public synchronized BookingRequest getRequest() {
        return queue.poll(); // returns null if empty
    }
}

// Worker Thread (Concurrent Booking Processor)
class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    public BookingProcessor(String name, BookingQueue queue, RoomInventory inventory) {
        super(name);
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            BookingRequest request;

            // Critical section: retrieving request
            synchronized (queue) {
                request = queue.getRequest();
            }

            if (request == null) {
                break; // no more requests
            }

            processBooking(request);
        }
    }

    private void processBooking(BookingRequest request) {
        boolean success = inventory.allocateRoom(request.getRoomType());

        if (success) {
            System.out.println(Thread.currentThread().getName() +
                    " successfully booked " + request.getRoomType() +
                    " for " + request.getGuestName());
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " failed to book " + request.getRoomType() +
                    " for " + request.getGuestName() + " (No availability)");
        }
    }
}

// Main Class
 class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        // Simulate multiple concurrent booking requests
        queue.addRequest(new BookingRequest("Alice", "Deluxe"));
        queue.addRequest(new BookingRequest("Bob", "Deluxe"));
        queue.addRequest(new BookingRequest("Charlie", "Standard"));
        queue.addRequest(new BookingRequest("David", "Standard"));
        queue.addRequest(new BookingRequest("Eve", "Suite"));
        queue.addRequest(new BookingRequest("Frank", "Suite"));

        // Create multiple threads (simulating concurrent users)
        BookingProcessor t1 = new BookingProcessor("Thread-1", queue, inventory);
        BookingProcessor t2 = new BookingProcessor("Thread-2", queue, inventory);
        BookingProcessor t3 = new BookingProcessor("Thread-3", queue, inventory);

        System.out.println("Starting concurrent booking simulation...\n");

        t1.start();
        t2.start();
        t3.start();

        // Wait for all threads to finish
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n--- Final State ---");
        inventory.displayInventory();

        System.out.println("\nAll bookings processed safely without race conditions.");
    }
}