package main.java.guild.state;

import main.java.guild.criminal.Criminal;

public class CaptureState implements HunterState {

    @Override
    public void performAction(HunterContext context, Criminal criminal) {
        System.out.println("[CAPTURE] " + context.getHunter().getName() + " initiating capture sequence...");
        System.out
                .println("[CAPTURE] Target: " + criminal.getAlias() + " - Threat Level: " + criminal.getThreatLevel());
        System.out.println("[CAPTURE] Moving into position...");

        context.getHunter().capture(criminal);

        System.out.println("[CAPTURE] Target secured and restrained");
        System.out.println("[CAPTURE] Preparing for transport to authorities");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        context.setActionCompleted(true);
        System.out.println("[CAPTURE] Mission completed successfully!");

        
        System.out.println("\n Final Mission Report");
        context.getHunter().reportStatus();
    }

    @Override
    public void nextState(HunterContext context) {
        if (context.isActionCompleted()) {
            context.setState(new MissionCompletedState());
        } else {
            System.out.println("[CAPTURE] Cannot transition - capture phase not completed");
        }
    }

    @Override
    public String getStateName() {
        return "CAPTURE";
    }
}
