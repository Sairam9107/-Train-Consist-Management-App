import java.util.HashSet;
import java.util.Set;

 class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        // Create HashSet for Unique Bogie IDs
        Set<String> bogieIds = new HashSet<>();

        // Adding Bogie IDs (Including Duplicates Intentionally)
        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");   // Duplicate
        bogieIds.add("BG102");   // Duplicate

        // Display Unique Bogie IDs
        System.out.println("\nUnique Bogie IDs in Train:");
        System.out.println(bogieIds);

        System.out.println("\nProgram continues...");
    }
}