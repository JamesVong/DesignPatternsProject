package guild.bounty;

import guild.criminal.Criminal;

public class MandalorianFactory extends BountyHunterFactory {

    @Override
    public BountyHunter createBountyHunter(String name, String rank) {
        return new MandalorianHunter(name, rank);
    }
}
