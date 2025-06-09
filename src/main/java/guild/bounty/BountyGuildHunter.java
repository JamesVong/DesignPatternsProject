package guild.bounty;

import guild.criminal.Criminal;

public class BountyGuildHunter extends BountyHunter {

    public BountyGuildHunter(String name, String specialty) {
        super(name, "Guild", specialty);
    }

    @Override
    public void track(Criminal target) {
        System.out.println("[Guild] " + name + " uses guild intel to track " +
                target.getAlias() + " at " + target.getLastKnownLocation());
    }

    @Override
    public void capture(Criminal target) {
        System.out.println("[Guild] " + name + " executes a bounty capture protocol on " +
                target.getAlias() + "!");
    }

    @Override
    public void reportStatus() {
        System.out.println("[Guild] " + name + " (" + rank + ") reports to Bounty Hunters' Guild HQ.");
    }
}
