package guild.strategy;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;
import guild.decorator.GadgetDecorator;
import guild.decorator.WeaponDecorator;

public class MediumThreatStrategy implements CaptureStrategy {

    @Override
    public BountyHunter equipHunter(BountyHunter hunter, Criminal criminal) {
        System.out.println("MEDIUM THREAT detected - Equipping gadgets and weapons");
        BountyHunter equipped = new GadgetDecorator(hunter);
        equipped = new WeaponDecorator(equipped);
        return equipped;
    }

    @Override
    public void executeCapture(BountyHunter equippedHunter, Criminal criminal) {
        System.out.println(" Medium Threat Capture Operation ");
        equippedHunter.track(criminal);
        equippedHunter.capture(criminal);
        System.out.println("Medium threat capture completed with enhanced equipment!");
    }
}
