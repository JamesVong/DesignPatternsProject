package guild.bounty;

public class BountyGuildFactory extends BountyHunterFactory {

    @Override
    public BountyHunter createBountyHunter(String name, String specialty) {
        return new BountyGuildHunter(name, specialty);
    }
}
