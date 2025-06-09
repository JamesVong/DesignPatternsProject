import guild.facade.MissionFacade;
import guild.criminal.Criminal;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // File path for the CSV file
        String criminalFilePath = "src/main/java/resources/criminals.csv";

        // Initialize the MissionFacade
        MissionFacade missionFacade = new MissionFacade(criminalFilePath);

        // Fetch criminals
        List<Criminal> criminals = missionFacade.fetchCriminals();
        System.out.println("Fetched Criminals:");
        for (Criminal criminal : criminals) {
            System.out.println(criminal);
        }

        // Recruit hunters and display initial status
        missionFacade.recruitHunters();
        System.out.println("\n" + "=".repeat(80));
        missionFacade.displaySchedulerStatus();

        // Add missions
        System.out.println("Adding high-priority mission...");
        missionFacade.addMission(criminals.get(2), 8); // Jabba Desilijic Tiure

        System.out.println("\nAdding medium-priority mission...");
        missionFacade.addMission(criminals.get(1), 5); // Jango Fett

        System.out.println("\nAdding low-priority mission...");
        missionFacade.addMission(criminals.get(0), 3); // Greedo

        // Wait for missions to complete
        System.out.println("\nWaiting for missions to complete...\n");
        missionFacade.waitForMissions(3000);
        missionFacade.displaySchedulerStatus();

        // Set hunters as unavailable and add more missions
        missionFacade.setHunterUnavailable("Din Djarin", "Equipment maintenance");
        missionFacade.setHunterUnavailable("Boba Fett", "Medical check-up");
        missionFacade.displaySchedulerStatus();

        System.out.println("Adding missions while hunters are busy...");
        missionFacade.addMission(criminals.get(3), 10); // Darth Maul
        missionFacade.addMission(criminals.get(4), 6);  // Cad Bane

        missionFacade.displaySchedulerStatus();

        // Set hunters as available
        System.out.println("Din Djarin finishing maintenance...");
        missionFacade.setHunterAvailable("Din Djarin");

        System.out.println("\nBoba Fett finishing medical check...");
        missionFacade.setHunterAvailable("Boba Fett");

        // Wait for auto-assignments
        missionFacade.waitForMissions(2000);
        missionFacade.displaySchedulerStatus();
    }
}