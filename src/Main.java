import java.util.ArrayList;
import java.util.List;

public class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        List<String> bogies = new ArrayList<>();

        System.out.println("Train consist initialized.");
        System.out.println("Initial number of bogies: " + bogies.size());
    }
}
import java.util.ArrayList;
import java.util.List;

public class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        List<String> passengerBogies = new ArrayList<>();

        passengerBogies.add("Sleeper");
        passengerBogies.add("AC Chair");
        passengerBogies.add("First Class");

        System.out.println("Passenger bogies after addition: " + passengerBogies);

        passengerBogies.remove("AC Chair");

        System.out.println("Passenger bogies after removal: " + passengerBogies);

        boolean exists = passengerBogies.contains("Sleeper");
        System.out.println("Is Sleeper present? " + exists);

        System.out.println("Final bogie list: " + passengerBogies);
    }
}

import java.util.HashSet;
import java.util.Set;

public class TrainConsistApp {

    public static void main(String[] args) {

        System.out.println("=== Train Consist Management App ===");

        Set<String> bogieIds = new HashSet<>();

        bogieIds.add("BG101");
        bogieIds.add("BG102");
        bogieIds.add("BG103");
        bogieIds.add("BG101");
        bogieIds.add("BG102");

        System.out.println("Unique Bogie IDs: " + bogieIds);
    }
}
