package guild.strategy;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;
import guild.decorator.GadgetDecorator;

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