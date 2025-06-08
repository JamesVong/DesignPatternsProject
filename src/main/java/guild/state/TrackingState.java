package main.java.guild.state;

import main.java.guild.criminal.Criminal;

public class TrackingState implements HunterState {

    @Override
    public void performAction(HunterContext context, Criminal criminal) {
        System.out.println("[TRACKING] " + context.getHunter().getName() + " begins tracking operation...");
        System.out.println("[TRACKING] Scanning for traces of " + criminal.getAlias() + "...");

        
        context.getHunter().track(criminal);

        System.out.println("[TRACKING] Movement patterns analyzed");
        System.out.println("[TRACKING] Target location confirmed: " + criminal.getLastKnownLocation());
        System.out.println("[TRACKING] Approach vector calculated");

        
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        context.setActionCompleted(true);
        System.out.println("[TRACKING] Target successfully located and tracked");
    }

    @Override
    public void nextState(HunterContext context) {
        if (context.isActionCompleted()) {
            context.setState(new CaptureState());
        } else {
            System.out.println("[TRACKING] Cannot transition - tracking phase not completed");
        }
    }

    @Override
    public String getStateName() {
        return "TRACKING";
    }
}