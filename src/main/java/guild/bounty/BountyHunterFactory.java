package guild.bounty;

import guild.criminal.Criminal;

public abstract class BountyHunterFactory {

    // Factory method - subclasses will implement this
    public abstract BountyHunter createBountyHunter(String name, String rank);

    // Template method that uses the factory method
    public BountyHunter recruitHunter(String name, String rank) {
        BountyHunter hunter = createBountyHunter(name, rank);
        System.out.println("Recruited " + hunter.getName() + " from " + hunter.getFaction());
        return hunter;
    }
}