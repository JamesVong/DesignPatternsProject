package guild.mission;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;
import guild.context.CaptureContext;
import guild.state.HunterContext;
import guild.state.EquippedState;
import guild.state.TrackingState;
import guild.state.CaptureState;
import guild.state.MissionCompletedState;

public class MissionManager {

    public void executeMissionWithStates(BountyHunter hunter, Criminal criminal) {
        System.out.println("\n" + "=".repeat(100));
        System.out.println("Starting Mission");
        System.out.println("Hunter: " + hunter.getName() + " | Target: " + criminal.getAlias());
        System.out.println("=".repeat(100));

        // First, equip the hunter using Strategy + Decorator patterns
        CaptureContext captureContext = new CaptureContext();
        System.out.println("\nEQUIPMENT PREPARATION");
        BountyHunter equippedHunter = captureContext.equipHunterOnly(hunter, criminal);

        // Create hunter context with state management
        HunterContext hunterContext = new HunterContext(equippedHunter);

        // Execute mission phases with state transitions
        System.out.println("\n STATE-BASED MISSION EXECUTION");

        // Phase 1: Equipped State
        executePhase(hunterContext, criminal, "EQUIPMENT PREPARATION");

        // Phase 2: Tracking State
        executePhase(hunterContext, criminal, "TARGET TRACKING");

        // Phase 3: Capture State
        executePhase(hunterContext, criminal, "TARGET CAPTURE");

        // Phase 4: Mission Completed
        executePhase(hunterContext, criminal, "MISSION COMPLETION");

        System.out.println("\n" + "=".repeat(100));
        System.out.println("Mission Completed");
        System.out.println("=".repeat(100));
    }

    private void executePhase(HunterContext context, Criminal criminal, String phaseName) {
        System.out.println("\nExecuting " + phaseName + " Phase");
        System.out.println("Current State: " + context.getCurrentState().getStateName());

        // Perform current state action
        context.performCurrentAction(criminal);

        // Try to proceed to next state
        if (context.canProceed()) {
            context.proceedToNextState();
        }

        // Add visual separator
        System.out.println("─".repeat(80));
    }
}