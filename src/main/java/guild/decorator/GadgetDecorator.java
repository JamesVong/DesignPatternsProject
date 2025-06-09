package guild.decorator;

import guild.availability.AvailabilityState;
import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public class GadgetDecorator extends BountyHunterDecorator {

    public GadgetDecorator(BountyHunter hunter) {
        super(hunter);
    }

    @Override
    public void setAvailability(AvailabilityState newState) {
        super.setAvailability(newState);
    }

    @Override
    public void track(Criminal target) {
        System.out.println("[GADGETS] Activating thermal scanner and motion detectors...");
        super.track(target);
        System.out.println("[GADGETS] Target locked with enhanced tracking technology");
    }

    @Override
    public void capture(Criminal target) {
        System.out.println("[GADGETS] Deploying stun net and restraining devices...");
        super.capture(target);
        System.out.println("[GADGETS] Target secured with advanced restraints");
    }

    @Override
    public void reportStatus() {
        System.out.println("[GADGETS] All tracking and capture gadgets operational");
        super.reportStatus();
    }
}
