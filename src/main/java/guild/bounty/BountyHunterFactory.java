package guild.bounty;

import guild.criminal.Criminal;

public abstract class BountyHunterFactory {

    public abstract BountyHunter createBountyHunter(String name, String rank);

    public BountyHunter recruitHunter(String name, String rank) {
        BountyHunter hunter = createBountyHunter(name, rank);
        System.out.println("Recruited " + hunter.getName() + " from " + hunter.getFaction());
        return hunter;
    }
}