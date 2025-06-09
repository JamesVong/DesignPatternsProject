//package guild;

import guild.bounty.BountyHunter;
import guild.bounty.BountyHunterFactory;
import guild.bounty.MandalorianFactory;
import guild.bounty.ImperialFactory;
import guild.criminal.Criminal;
import guild.scheduler.MissionScheduler;
import guild.availability.UnavailableState;
import guild.adapter.CriminalAdapter;
import guild.database.Database;
import guild.database.DatabaseProxy;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // File paths for the CSV files
        String criminalFilePath = "src/main/java/resources/criminals.csv";

        // Create DatabaseProxy objects for criminals
        Database criminalDatabase = new DatabaseProxy(criminalFilePath);

        // Use the CriminalAdapter to read and convert criminal data
        CriminalAdapter criminalAdapter = new CriminalAdapter(criminalDatabase);
        List<Criminal> criminals = criminalAdapter.getCriminals();

        // Display fetched data
        System.out.println("Fetched Criminals:");
        for (Criminal criminal : criminals) {
            System.out.println(criminal);
        }

        // Create factories and hunters from the fetched bounty hunters
        BountyHunterFactory mandalorianFactory = new MandalorianFactory();
        BountyHunterFactory imperialFactory = new ImperialFactory();

        BountyHunter dinDjarin = mandalorianFactory.recruitHunter("Din Djarin", "Silver");
        BountyHunter bobaFett = mandalorianFactory.recruitHunter("Boba Fett", "Gold");
        BountyHunter imperialAgent = imperialFactory.recruitHunter("Agent Kallus", "Commander");

        // Create and setup mission scheduler
        MissionScheduler scheduler = new MissionScheduler();

        // Register hunters with scheduler (this sets up observer relationships)
        scheduler.registerHunter(dinDjarin);
        scheduler.registerHunter(bobaFett);
        scheduler.registerHunter(imperialAgent);

        System.out.println("\n" + "=".repeat(80));
        System.out.println("=".repeat(80));
        scheduler.displayStatus();

        // Add missions to queue when hunters are available
        System.out.println("=".repeat(80));

        System.out.println("Adding high-priority mission...");
        scheduler.addMission(criminals.get(2), 8); // Jabba Desilijic Tiure

        System.out.println("\nAdding medium-priority mission...");
        scheduler.addMission(criminals.get(1), 5); // Jango Fett

        System.out.println("\nAdding low-priority mission...");
        scheduler.addMission(criminals.get(0), 3); // Greedo

        // Wait a moment for missions to complete
        System.out.println("\nWaiting for missions to complete...\n");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scheduler.displayStatus();

        // Add missions when hunters are busy
        // Manually set some hunters as unavailable
        dinDjarin.setAvailability(new UnavailableState("Equipment maintenance"));
        bobaFett.setAvailability(new UnavailableState("Medical check-up"));

        scheduler.displayStatus();

        System.out.println("Adding missions while hunters are busy...");
        scheduler.addMission(criminals.get(3), 10); // Darth Maul
        scheduler.addMission(criminals.get(4), 6);  // Cad Bane

        scheduler.displayStatus();

        // Hunter becomes available and gets auto-assigned
        System.out.println("Din Djarin finishing maintenance...");
        dinDjarin.setAvailability(new guild.availability.AvailableState());

        System.out.println("\nBoba Fett finishing medical check...");
        bobaFett.setAvailability(new guild.availability.AvailableState());

        // Wait for auto-assignments
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        scheduler.displayStatus();
    }
}