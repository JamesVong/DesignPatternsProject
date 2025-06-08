package main.java.guild.strategy;

import main.java.guild.bounty.BountyHunter;
import main.java.guild.criminal.Criminal;
import main.java.guild.decorator.GadgetDecorator;
import main.java.guild.decorator.WeaponDecorator;

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
