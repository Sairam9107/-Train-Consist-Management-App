import java.util.*;

// Bogie Class
class Bogie {
    private String name;
    private int capacity;

    public Bogie(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return name + " - Capacity: " + capacity;
    }
}

// Main Class
public class UC10TotalSeatCount {

    public static void main(String[] args) {

        // Step 1: Create Bogie List
        List<Bogie> bogies = new ArrayList<>();

        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));
        bogies.add(new Bogie("Sleeper", 72));

        System.out.println("Bogie List:");
        bogies.forEach(System.out::println);

        // Step 2: Stream Pipeline → map() + reduce()
        int totalSeats = bogies.stream()
                .map(b -> b.getCapacity())     // Extract capacity
                .reduce(0, Integer::sum);      // Aggregate (sum)

        // Step 3: Display Result
        System.out.println("\nTotal Seating Capacity: " + totalSeats);

        // Step 4: Verify original list unchanged
        System.out.println("\nOriginal List After Aggregation (Unchanged):");
        bogies.forEach(System.out::println);
    }
}
