import java.util.LinkedHashSet;

 class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create LinkedHashSet for Train Formation
        LinkedHashSet<String> trainFormation = new LinkedHashSet<>();

        // Attach Bogies
        trainFormation.add("Engine");
        trainFormation.add("Sleeper");
        trainFormation.add("Cargo");
        trainFormation.add("Guard");

        // Attempt Duplicate Attachment
        trainFormation.add("Sleeper");   // Duplicate (Ignored)

        // Display Final Formation
        System.out.println("\nFinal Train Formation (Insertion Order Preserved):");
        System.out.println(trainFormation);

        System.out.println("\nProgram continues...");
    }
}