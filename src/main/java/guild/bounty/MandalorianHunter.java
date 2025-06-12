package guild.bounty;

import guild.criminal.Criminal;

public class MandalorianHunter extends BountyHunter {

    public MandalorianHunter(String name, String rank) {
        super(name, "Mandalorian", rank);
    }

    @Override
    public void track(Criminal target) {
        System.out.println("[Mandalorian] " + name + " uses advanced tracking technology to locate " +
                target.getAlias() + " at " + target.getLastKnownLocation());
        weapon.use();
    }

    @Override
    public void capture(Criminal target) {
        System.out.println("[Mandalorian] " + name + " uses beskar armor and jetpack to capture " +
                target.getAlias() + " - This is the Way!");
    }

    @Override
    public void reportStatus() {
        System.out.println("[Mandalorian] " + name + " (" + rank + ") is ready for the next hunt.");
    }
}