package guild.state;

import guild.criminal.Criminal;

public class MissionCompletedState implements HunterState {

    @Override
    public void performAction(HunterContext context, Criminal criminal) {
        System.out.println("[COMPLETED] Mission accomplished!");
        System.out.println("[COMPLETED] " + criminal.getAlias() + " has been successfully captured");
        System.out.println("[COMPLETED] " + context.getHunter().getName() + " is ready for next assignment");
    }

    @Override
    public void nextState(HunterContext context) {
        System.out.println("[COMPLETED] Mission is complete - no further transitions available");
    }

    @Override
    public String getStateName() {
        return "Mission Completed";
    }
}