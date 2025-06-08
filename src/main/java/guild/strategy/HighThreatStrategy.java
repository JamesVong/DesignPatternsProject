package guild.strategy;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;
import guild.decorator.GadgetDecorator;
import guild.decorator.WeaponDecorator;
import guild.decorator.BackupDecorator;

public class HighThreatStrategy implements CaptureStrategy {

    @Override
    public BountyHunter equipHunter(BountyHunter hunter, Criminal criminal) {
        System.out.println("HIGH THREAT detected - Full equipment loadout with backup units");
        BountyHunter equipped = new GadgetDecorator(hunter);
        equipped = new WeaponDecorator(equipped);
        equipped = new BackupDecorator(equipped);
        return equipped;
    }

    @Override
    public void executeCapture(BountyHunter equippedHunter, Criminal criminal) {
        System.out.println(" High Threat Capture Operation ");
        System.out.println("Coordinating with backup units...");
        equippedHunter.track(criminal);
        equippedHunter.capture(criminal);
        System.out.println("High threat capture completed with full tactical support!");
    }
}