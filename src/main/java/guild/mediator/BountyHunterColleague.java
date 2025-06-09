package guild.mediator;

import guild.bounty.BountyHunter;
import guild.builder.MissionProfile;

public class BountyHunterColleague extends Colleague {

    private BountyHunter hunter;

    public BountyHunterColleague(BountyHunter hunter, MissionMediator mediator) {
        super(mediator);
        this.hunter = hunter;
    }

    @Override
    public void receiveMission(MissionProfile profile) {
        System.out.println("Hunter " + hunter.getName() + " preparing for mission against: " + profile.getCriminal().getName());
    }

    public void receiveIntel(String intel) {
        System.out.println("Hunter received intel: " + intel);
    }

    public void requestIntel() {
        System.out.println("Hunter requesting drone intel...");
        mediator.notify("drone_request", this);
    }
}
