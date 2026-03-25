import java.util.*;

// Bogie Class (Custom Object)
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
public class UC7SortBogiesByCapacity {

    public static void main(String[] args) {

        // Step 1: Create List of Bogies
        List<Bogie> bogies = new ArrayList<>();

        // Step 2: Add Passenger Bogies
        bogies.add(new Bogie("Sleeper", 72));
        bogies.add(new Bogie("AC Chair", 56));
        bogies.add(new Bogie("First Class", 24));

        System.out.println("Before Sorting:");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Step 3: Sort using Comparator (Ascending Order)
        bogies.sort(Comparator.comparingInt(Bogie::getCapacity));

        System.out.println("\nAfter Sorting (By Capacity - Ascending):");
        for (Bogie b : bogies) {
            System.out.println(b);
        }

        // Optional: Descending Order (for planning highest capacity first)
        bogies.sort(Comparator.comparingInt(Bogie::getCapacity).reversed());

        System.out.println("\nAfter Sorting (By Capacity - Descending):");
        for (Bogie b : bogies) {
            System.out.println(b);
        }
    }
}
