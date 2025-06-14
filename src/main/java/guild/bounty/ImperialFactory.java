package guild.bounty;

import guild.criminal.Criminal;

public class ImperialFactory extends BountyHunterFactory {

    @Override
    public BountyHunter createBountyHunter(String name, String rank) {
        return new ImperialHunter(name, rank);
    }
}
