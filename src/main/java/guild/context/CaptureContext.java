package guild.context;

import guild.bounty.BountyHunter;
import guild.criminal.Criminal;
import guild.strategy.CaptureStrategy;
import guild.strategy.StrategySelector;

public class CaptureContext {
    private CaptureStrategy strategy;

    public void executeMission(BountyHunter hunter, Criminal criminal) {
        System.out.println("\n Mission Briefing");
        System.out.println("Hunter: " + hunter.getName() + " (" + hunter.getFaction() + ")");
        System.out.println("Target: " + criminal.getAlias() + " (Threat Level: " + criminal.getThreatLevel() + ")");

        strategy = StrategySelector.selectStrategy(criminal);
        BountyHunter equippedHunter = strategy.equipHunter(hunter, criminal);

        System.out.println("\nMission Execution");
        strategy.executeCapture(equippedHunter, criminal);

        System.out.println("\n Post Mission Report");
        equippedHunter.reportStatus();
    }

    public BountyHunter equipHunterOnly(BountyHunter hunter, Criminal criminal) {
        System.out.println("Equipment Briefing");
        System.out.println("Hunter: " + hunter.getName() + " (" + hunter.getFaction() + ")");
        System.out.println("Target: " + criminal.getAlias() + " (Threat Level: " + criminal.getThreatLevel() + ")");

        strategy = StrategySelector.selectStrategy(criminal);
        return strategy.equipHunter(hunter, criminal);
    }
}
