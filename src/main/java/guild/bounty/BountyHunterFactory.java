package guild.bounty;

import guild.criminal.Criminal;
import guild.singleton.GuildRegistry;

public abstract class BountyHunterFactory {

    public abstract BountyHunter createBountyHunter(String name, String rank);

    public BountyHunter recruitHunter(String name, String rank) {
        BountyHunter hunter = createBountyHunter(name, rank);
        GuildRegistry.getInstance().registerHunter(name, hunter);
        hunter.setRank(rank);
        System.out.println("Recruited " + hunter.getName() + " from " + hunter.getFaction());
        return hunter;
    }
}