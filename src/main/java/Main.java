////package guild;
//
//import main.java.guild.bounty.BountyHunter;
//import main.java.guild.bounty.BountyHunterFactory;
//import main.java.guild.bounty.MandalorianFactory;
//import main.java.guild.bounty.ImperialFactory;
//import main.java.guild.criminal.Criminal;
//import main.java.guild.scheduler.MissionScheduler;
//import main.java.guild.availability.UnavailableState;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        // Create criminals with different threat levels
//        Criminal lowThreat = new Criminal("Greedo", "The Green Gunslinger", 2, "Mos Eisley Cantina");
//        Criminal mediumThreat = new Criminal("Jango Fett", "The Original", 5, "Kamino");
//        Criminal highThreat = new Criminal("Jabba Desilijic Tiure", "Jabba the Hutt", 9, "Tatooine Palace");
//        Criminal veryHighThreat = new Criminal("Darth Maul", "The Shadow", 10, "Dathomir");
//        Criminal anotherMedium = new Criminal("Cad Bane", "The Duros Gunslinger", 6, "Tatooine Outskirts");
//
//        // Create factories and hunters
//        BountyHunterFactory mandalorianFactory = new MandalorianFactory();
//        BountyHunterFactory imperialFactory = new ImperialFactory();
//
//        BountyHunter dinDjarin = mandalorianFactory.recruitHunter("Din Djarin", "Silver");
//        BountyHunter bobaFett = mandalorianFactory.recruitHunter("Boba Fett", "Gold");
//        BountyHunter imperialAgent = imperialFactory.recruitHunter("Agent Kallus", "Commander");
//
//        // Create and setup mission scheduler
//        MissionScheduler scheduler = new MissionScheduler();
//
//        // Register hunters with scheduler (this sets up observer relationships)
//        scheduler.registerHunter(dinDjarin);
//        scheduler.registerHunter(bobaFett);
//        scheduler.registerHunter(imperialAgent);
//
//        System.out.println("\n" + "=".repeat(80));
//        System.out.println("=".repeat(80));
//        scheduler.displayStatus();
//
//        // Add missions to queue when hunters are available
//
//        System.out.println("=".repeat(80));
//
//        System.out.println("Adding high-priority mission...");
//        scheduler.addMission(highThreat, 8);
//
//        System.out.println("\nAdding medium-priority mission...");
//        scheduler.addMission(mediumThreat, 5);
//
//        System.out.println("\nAdding low-priority mission...");
//        scheduler.addMission(lowThreat, 3);
//
//        // Wait a moment for missions to complete
//        System.out.println("\nWaiting for missions to complete...\n");
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        scheduler.displayStatus();
//
//        // Add missions when hunters are busy
//
//        // Manually set some hunters as unavailable
//        dinDjarin.setAvailability(new UnavailableState("Equipment maintenance"));
//        bobaFett.setAvailability(new UnavailableState("Medical check-up"));
//
//        scheduler.displayStatus();
//
//        System.out.println("Adding missions while hunters are busy...");
//        scheduler.addMission(veryHighThreat, 10);
//        scheduler.addMission(anotherMedium, 6);
//
//        scheduler.displayStatus();
//
//        // Hunter becomes available and gets auto-assigned
//
//        System.out.println("Din Djarin finishing maintenance...");
//        dinDjarin.setAvailability(new guild.availability.AvailableState());
//
//        System.out.println("\nBoba Fett finishing medical check...");
//        bobaFett.setAvailability(new guild.availability.AvailableState());
//
//        // Wait for auto-assignments
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        scheduler.displayStatus();
//
//    }
//}
