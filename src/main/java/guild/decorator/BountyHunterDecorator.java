package guild.decorator;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;

public abstract class BountyHunterDecorator extends BountyHunter {
    protected BountyHunter wrappedHunter;

    public BountyHunterDecorator(BountyHunter hunter) {
        super(hunter.getName(), hunter.getFaction(), hunter.getRank());
        this.wrappedHunter = hunter;
    }

    @Override
    public void track(Criminal target) {
        wrappedHunter.track(target);
    }

    @Override
    public void capture(Criminal target) {
        wrappedHunter.capture(target);
    }

    @Override
    public void reportStatus() {
        wrappedHunter.reportStatus();
    }
}