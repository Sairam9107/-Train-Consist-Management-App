import java.util.LinkedList;

 class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create LinkedList for Train Consist
        LinkedList<String> trainConsist = new LinkedList<>();

        // Add Bogies (Initial Formation)
        trainConsist.add("Engine");
        trainConsist.add("Sleeper");
        trainConsist.add("AC");
        trainConsist.add("Cargo");
        trainConsist.add("Guard");

        System.out.println("\nInitial Train Consist:");
        System.out.println(trainConsist);

        // Insert Pantry Car at Position 2
        trainConsist.add(2, "Pantry");

        System.out.println("\nAfter inserting Pantry Car at position 2:");
        System.out.println(trainConsist);

        // Remove First and Last Bogie
        trainConsist.removeFirst();
        trainConsist.removeLast();

        // Final Ordered Consist
        System.out.println("\nFinal Train Consist:");
        System.out.println(trainConsist);

        System.out.println("\nProgram continues...");
    }
}