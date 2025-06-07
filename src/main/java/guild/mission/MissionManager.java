package guild.mission;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;
import guild.context.CaptureContext;
import guild.state.HunterContext;
import guild.availability.AvailableState;
import guild.availability.UnavailableState;

public class MissionManager {

    public void executeMissionWithStates(BountyHunter hunter, Criminal criminal) {
        // Check if hunter is available
        if (!hunter.isAvailable()) {
            System.out.println(
                    "[MISSION] Cannot start mission - " + hunter.getName() + " is " + hunter.getAvailabilityStatus());
            System.out.println("   Reason: " + hunter.getAvailabilityState().getDescription());
            return;
        }

        System.out.println("\n" + "=".repeat(100));
        System.out.println("STARTING MISSION WITH STATE PATTERN");
        System.out.println("Hunter: " + hunter.getName() + " | Target: " + criminal.getAlias());
        System.out.println("=".repeat(100));

        // Set hunter to unavailable when mission starts
        hunter.setAvailability(new UnavailableState("Active mission: " + criminal.getAlias()));

        try {
            // First, equip the hunter using Strategy + Decorator patterns
            CaptureContext captureContext = new CaptureContext();
            System.out.println("\nEQUIPMENT PREPARATION");
            BountyHunter equippedHunter = captureContext.equipHunterOnly(hunter, criminal);

            // Create hunter context with state management
            HunterContext hunterContext = new HunterContext(equippedHunter);

            // Execute mission phases with state transitions
            System.out.println("\nSTATE-BASED MISSION EXECUTION");

            // Phase 1: Equipped State
            executePhase(hunterContext, criminal, "EQUIPMENT PREPARATION");

            // Phase 2: Tracking State
            executePhase(hunterContext, criminal, "TARGET TRACKING");

            // Phase 3: Capture State
            executePhase(hunterContext, criminal, "TARGET CAPTURE");

            // Phase 4: Mission Completed
            executePhase(hunterContext, criminal, "MISSION COMPLETION");

            System.out.println("\n" + "=".repeat(100));
            System.out.println("MISSION SUCCESSFULLY COMPLETED!");
            System.out.println("=".repeat(100));

        } finally {
            // Always set hunter back to available when mission ends (success or failure)
            hunter.setAvailability(new AvailableState());
        }
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

    }
}