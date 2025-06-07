//package guild;

import guild.bounty.BountyHunter;
import guild.bounty.BountyHunterFactory;
import guild.bounty.ImperialFactory;
import guild.bounty.MandalorianFactory;
import guild.criminal.Criminal;

public class Main {

    // A simple subclass of BountyHunter for testing
    static class TestHunter extends BountyHunter {

        public TestHunter(String name, String faction, String rank) {
            super(name, faction, rank);
        }

        @Override
        public void track(Criminal target) {
            System.out.println(name + " is tracking " + target.getAlias() + " at " + target.getLastKnownLocation());
        }

        @Override
        public void capture(Criminal target) {
            System.out.println(name + " has captured " + target.getAlias());
        }

        @Override
        public void reportStatus() {
            System.out.println(name + " is currently idle.");
        }
    }

    public static void main(String[] args) {
        // Create a criminal
        Criminal jabba = new Criminal("Jabba Desilijic Tiure", "Jabba the Hutt", 9, "Tatooine");

        // Display criminal info
        System.out.println("=== Criminal Profile ===");
        jabba.displayProfile();

        // Create factories
        BountyHunterFactory mandalorianFactory = new MandalorianFactory();
        BountyHunterFactory imperialFactory = new ImperialFactory();

        BountyHunter dinDjarin = mandalorianFactory.recruitHunter("Din Djarin", "Silver");
        BountyHunter bobaFett = mandalorianFactory.recruitHunter("Boba Fett", "Gold");
        BountyHunter imperialAgent = imperialFactory.recruitHunter("Agent Kallus", "Commander");

        // Create a bounty hunter
        // BountyHunter hunter = new TestHunter("Din Djarin", "Mandalorian", "Silver");

        // Simulate actions
        System.out.println("\n=== Bounty Hunter Actions ===");
        dinDjarin.track(jabba);
        dinDjarin.capture(jabba);
        dinDjarin.reportStatus();
    }
}
