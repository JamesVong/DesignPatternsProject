package guild.mediator;

import guild.builder.MissionProfile;

public class Drone extends Colleague {

    private String droneId;

    public Drone(String droneId, MissionMediator mediator) {
        super(mediator);
        this.droneId = droneId;
    }

    @Override
    public void receiveMission(MissionProfile profile) {
        System.out.println("Drone " + droneId + " deployed for recon on: " + profile.getCriminal().getName());
    }

    public void sendIntel() {
        System.out.println("Drone " + droneId + " scanning the target area...");
        // Simulate analysis done
        mediator.notify("intel_ready", this);
    }
}
