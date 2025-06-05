package guild;

import guild.bounty.BountyHunter;
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

        // Create a bounty hunter
        BountyHunter hunter = new TestHunter("Din Djarin", "Mandalorian", "Silver");

        // Simulate actions
        System.out.println("\n=== Bounty Hunter Actions ===");
        hunter.track(jabba);
        hunter.capture(jabba);
        hunter.reportStatus();
    }
}
