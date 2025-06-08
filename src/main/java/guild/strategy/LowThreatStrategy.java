package main.java.guild.strategy;

import main.java.guild.bounty.BountyHunter;
import main.java.guild.criminal.Criminal;
import main.java.guild.decorator.GadgetDecorator;

public class LowThreatStrategy implements CaptureStrategy {

    @Override
    public BountyHunter equipHunter(BountyHunter hunter, Criminal criminal) {
        System.out.println("LOW THREAT detected - Equipping basic gadgets only");
        return new GadgetDecorator(hunter);
    }

    @Override
    public void executeCapture(BountyHunter equippedHunter, Criminal criminal) {
        System.out.println("Low Threat Capture Operation");
        equippedHunter.track(criminal);
        equippedHunter.capture(criminal);
        System.out.println("Low threat capture completed successfully!");
    }
}