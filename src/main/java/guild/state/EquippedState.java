package guild.state;

import guild.criminal.Criminal;

public class EquippedState implements HunterState {

    @Override
    public void performAction(HunterContext context, Criminal criminal) {
        System.out.println(
                "[EQUIPPED] " + context.getHunter().getName() + " is fully equipped and analyzing target...");
        System.out.println("[EQUIPPED] Target Analysis: " + criminal.getAlias() + " (Threat Level: "
                + criminal.getThreatLevel() + ")");
        System.out.println("[EQUIPPED] Last known location: " + criminal.getLastKnownLocation());
        System.out.println("[EQUIPPED] Equipment check complete - ready to begin tracking");

        // Simulate equipment preparation time
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        context.setActionCompleted(true);
        System.out.println("[EQUIPPED] Equipment phase completed successfully");
    }

    @Override
    public void nextState(HunterContext context) {
        if (context.isActionCompleted()) {
            context.setState(new TrackingState());
        } else {
            System.out.println("[EQUIPPED] Cannot transition - equipment phase not completed");
        }
    }

    @Override
    public String getStateName() {
        return "EQUIPPED";
    }
}