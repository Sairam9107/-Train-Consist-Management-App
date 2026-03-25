import java.util.ArrayList;
import java.util.List;

class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create ArrayList for Passenger Bogies
        List<String> passengerBogies = new ArrayList<>();

        // ADD Operations
        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        // Display After Insertion
        System.out.println("\nPassenger Bogies after addition:");
        System.out.println(passengerBogies);

        // REMOVE Operation (Removing AC Chair)
        passengerBogies.remove("AC Chair");

        System.out.println("\nPassenger Bogies after removal of AC Chair:");
        System.out.println(passengerBogies);

        // CONTAINS Operation
        if (passengerBogies.contains("Sleeper")) {
            System.out.println("\nSleeper bogie exists in the train.");
        } else {
            System.out.println("\nSleeper bogie does not exist.");
        }

        // Final State
        System.out.println("\nFinal Passenger Bogie List:");
        System.out.println(passengerBogies);

        System.out.println("\nProgram continues...");
    }
}