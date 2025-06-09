package guild.mediator;

import guild.builder.MissionProfile;

import java.util.ArrayList;
import java.util.List;

public class MissionControl implements MissionMediator {

    private BountyHunterColleague hunter;
    private Drone drone;
    private List<Colleague> colleagues = new ArrayList<>();


    public void registerHunter(BountyHunterColleague hunter) {
        this.hunter = hunter;
    }

    public void registerDrone(Drone drone) {
        this.drone = drone;
    }

    @Override
    public void registerColleague(Colleague colleague) {
        colleagues.add(colleague);
    }

    @Override
    public void coordinateMission(MissionProfile profile) {
        System.out.println("\n[Mediator] Coordinating mission: " + profile.getCriminal().getName());
        for (Colleague colleague : colleagues) {
            colleague.receiveMission(profile);
        }
    }

    @Override
    public void notify(String event, Colleague sender) {
        if (event.equals("drone_request")) {
            System.out.println("MissionControl: Drone requested by hunter. Triggering scan.");
            drone.sendIntel();
        } else if (event.equals("intel_ready")) {
            System.out.println("MissionControl: Intel received from drone. Forwarding to hunter.");
            hunter.receiveIntel("Thermal scan data: 3 hostiles detected.");
        }
    }
}
