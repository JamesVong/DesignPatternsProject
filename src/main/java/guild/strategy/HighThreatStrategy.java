package main.java.guild.strategy;

import main.java.guild.bounty.BountyHunter;
import main.java.guild.criminal.Criminal;
import main.java.guild.decorator.GadgetDecorator;
import main.java.guild.decorator.WeaponDecorator;
import main.java.guild.decorator.BackupDecorator;

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