package main.java.guild.decorator;

import main.java.guild.bounty.BountyHunter;
import main.java.guild.criminal.Criminal;

public class WeaponDecorator extends BountyHunterDecorator {

    public WeaponDecorator(BountyHunter hunter) {
        super(hunter);
    }

    @Override
    public void track(Criminal target) {
        System.out.println("[WEAPONS] Armed and ready - blasters charged, weapons hot");
        super.track(target);
    }

    @Override
    public void capture(Criminal target) {
        System.out.println("[WEAPONS] Using controlled force - stun blasters engaged");
        super.capture(target);
        System.out.println("[WEAPONS] Target neutralized with non-lethal force");
    }

    @Override
    public void reportStatus() {
        System.out.println("[WEAPONS] All weapon systems armed and functional");
        super.reportStatus();
    }
}