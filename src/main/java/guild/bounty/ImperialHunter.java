package guild.bounty;

import guild.criminal.Criminal;

public class ImperialHunter extends BountyHunter {

    public ImperialHunter(String name, String rank) {
        super(name, "Imperial", rank);
    }

    @Override
    public void track(Criminal target) {
        System.out.println("[Imperial] " + name + " deploys Imperial resources to track " +
                target.getAlias() + " at " + target.getLastKnownLocation());
        weapon.use();
    }

    @Override
    public void capture(Criminal target) {
        System.out.println("[Imperial] " + name + " uses Imperial authority to capture " +
                target.getAlias() + " for the Empire!");
    }

    @Override
    public void reportStatus() {
        System.out.println("[Imperial] " + name + " (" + rank + ") reports to Imperial Command.");
    }
}