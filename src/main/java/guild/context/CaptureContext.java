package guild.context;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;
import guild.strategy.CaptureStrategy;
import guild.strategy.StrategySelector;

public class CaptureContext {
    private CaptureStrategy strategy;

    public void executeMission(BountyHunter hunter, Criminal criminal) {
        System.out.println("\n Mission Briefing ");
        System.out.println("Hunter: " + hunter.getName() + " (" + hunter.getFaction() + ")");
        System.out.println("Target: " + criminal.getAlias() + " (Threat Level: " + criminal.getThreatLevel() + ")");

        // Select strategy based on threat level
        strategy = StrategySelector.selectStrategy(criminal);

        // Equip hunter according to strategy
        BountyHunter equippedHunter = strategy.equipHunter(hunter, criminal);

        System.out.println("\n Mission Execution ");
        // Execute the capture with equipped hunter
        strategy.executeCapture(equippedHunter, criminal);

        System.out.println("\n Post Mission Report ");
        equippedHunter.reportStatus();
    }
}